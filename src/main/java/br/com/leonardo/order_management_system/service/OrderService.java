package br.com.leonardo.order_management_system.service;

import br.com.leonardo.order_management_system.dto.order.OrderCreateDTO;
import br.com.leonardo.order_management_system.dto.order.OrderDTO;
import br.com.leonardo.order_management_system.dto.order.OrderUpdateDTO;
import br.com.leonardo.order_management_system.dto.orderitem.OrderItemCreateDTO;
import br.com.leonardo.order_management_system.entity.*;
import br.com.leonardo.order_management_system.enums.OrderStatus;
import br.com.leonardo.order_management_system.exception.*;
import br.com.leonardo.order_management_system.mapper.OrderMapper;
import br.com.leonardo.order_management_system.repository.*;
import br.com.leonardo.order_management_system.service.freight.FreightInfo;
import br.com.leonardo.order_management_system.service.freight.FreightService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final OrderMapper orderMapper;
    private final FreightService freightService;
    private final ProductRepository productRepository;
    private final OrderTotalValueCalculate orderCalculate;

    public OrderService(OrderRepository orderRepository,
                        UserRepository userRepository,
                        AddressRepository addressRepository,
                        OrderMapper orderMapper,
                        FreightService freightService,
                        ProductRepository productRepository,
                        OrderTotalValueCalculate orderCalculate){
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.orderMapper = orderMapper;
        this.freightService = freightService;
        this.productRepository = productRepository;
        this.orderCalculate = orderCalculate;
    }

    public Order findOrderOrThrow(Long id){
        return orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found with ID: " + id));
    }

    public OrderDTO create(OrderCreateDTO dto){

        if(dto.getItems().isEmpty()) throw new FailedToCreateOrder("The order cannot be completed, list of items is empty.");

        Order order = orderMapper.toEntity(dto);
        Address address = addressRepository.findById(dto.getAddressId()).orElseThrow(() -> new EntityNotFoundException("Address not found with ID: " + dto.getAddressId()));
        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + dto.getUserId()));

        UUID uuid = UUID.randomUUID();
        LocalDateTime dateNow = LocalDateTime.now();
        String orderNumber = "ORD-" + uuid.toString().toUpperCase().substring(30, 36);
        FreightInfo info = freightService.calculateFreight(dto.getDeliveryType(), LocalDate.now());
        List<OrderItem> items = new ArrayList<>();

        for(OrderItemCreateDTO i: dto.getItems()){
            Product product = productRepository.findById(i.getProductId()).orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + i.getProductId()));
            items.add(new OrderItem(product, order, i.getQuantity(), product.getPrice()));
        }

        BigDecimal orderTotalValue = orderCalculate.calculateOrder(items, info.deliveryFee());

        order.setOrderNumber(orderNumber);
        order.setOrderDate(dateNow);
        order.setDeliveryDate(info.deliveryDate());
        order.setDeliveryType(info.deliveryType());
        order.setTotalValue(orderTotalValue);
        order.setDeliveryFee(info.deliveryFee());
        order.setOrderStatus(OrderStatus.CREATED);
        order.setOrderItemList(items);
        order.setAddress(address);
        order.setUser(user);

        Order savedOrder = orderRepository.save(order);
        return orderMapper.toDto(savedOrder);
    }

    public OrderDTO findById(Long id){
        Order order = findOrderOrThrow(id);
        return orderMapper.toDto(order);
    }

    public List<OrderDTO> findAllByUserId(Long id){
        return orderRepository.findAllByUserId(id).stream().map(orderMapper::toDto).toList();
    }

    public OrderDTO update(Long id, OrderUpdateDTO dto){
        Order orderExists = findOrderOrThrow(id);

        if(dto.getAddressId()!= null){
            Address address = addressRepository.findById(dto.getAddressId()).orElseThrow(() -> new EntityNotFoundException("Address not found with ID: " + dto.getAddressId()));
            if(!orderExists.getAddress().equals(address)){
                List<OrderStatus> statusUpdateAddressAvailable = new ArrayList<>(Arrays.asList(OrderStatus.CREATED, OrderStatus.PAYMENT_PENDING, OrderStatus.PAID, OrderStatus.PROCESSING, OrderStatus.RETURNED));
                if(statusUpdateAddressAvailable.contains(orderExists.getOrderStatus())){
                    orderExists.setAddress(address);
                } else {
                    throw new UpdateNotAvailable("It's not possible to change the address. Order already shipped or cancelled!");
                }
            }
        }

        if(dto.getDeliveryType() != null){
            FreightInfo info = freightService.calculateFreight(dto.getDeliveryType(), orderExists.getOrderDate().toLocalDate());
            if(!orderExists.getDeliveryType().equals(dto.getDeliveryType())){
                List<OrderStatus> statusUpdateTypeAvailable = new ArrayList<>(Arrays.asList(OrderStatus.CREATED, OrderStatus.PAYMENT_PENDING));
                if(statusUpdateTypeAvailable.contains(orderExists.getOrderStatus())){
                    orderExists.setDeliveryType(info.deliveryType());
                    orderExists.setDeliveryFee(info.deliveryFee());
                    orderExists.setDeliveryDate(info.deliveryDate());

                    BigDecimal orderTotalValue = orderCalculate.calculateOrder(orderExists.getOrderItemList(), info.deliveryFee());
                    orderExists.setTotalValue(orderTotalValue);
                } else {
                    throw new UpdateNotAvailable("It's not possible to change the delivery type. Order already paid!");
                }
            }
        }

        Order savedOrder = orderRepository.save(orderExists);
        return orderMapper.toDto(savedOrder);
    }

    public void cancel(Long id){
        Order orderExists = findOrderOrThrow(id);
        List<OrderStatus> statusDeleteOrderAvailable = new ArrayList<>(Arrays.asList(OrderStatus.CREATED, OrderStatus.PAYMENT_PENDING, OrderStatus.PAID, OrderStatus.PROCESSING, OrderStatus.DELIVERED, OrderStatus.RETURNED));

        if(statusDeleteOrderAvailable.contains(orderExists.getOrderStatus())){
            orderExists.setOrderStatus(OrderStatus.CANCELLED);
        }else {
            switch (orderExists.getOrderStatus()){
                case OrderStatus.SENT:
                    throw new FailedToCancelOrder("It's not possible to cancel the order. Order already shipped!");
                case OrderStatus.REFUNDED:
                    throw new FailedToCancelOrder("It's not possible to cancel the order. The order has already been refunded!");
                case OrderStatus.CANCELLED:
                    throw new FailedToCancelOrder("It's not possible to cancel the order. The order has already been cancelled!");
            }
        }

        orderRepository.save(orderExists);
    }

    public void refund(Long id){
        Order orderExists = findOrderOrThrow(id);

        if(orderExists.getOrderStatus().equals(OrderStatus.CANCELLED)){
            orderExists.setRefundedAt(LocalDateTime.now());
            orderExists.setRefundAmount(orderExists.getTotalValue());
            orderExists.setOrderStatus(OrderStatus.REFUNDED);
        } else {
            throw new FailedToRefundOrder("Failed to refund order!");
        }

        orderRepository.save(orderExists);
    }


    private static final Map<OrderStatus, Set<OrderStatus>> map = new EnumMap<>(OrderStatus.class);

    static {
        map.put(OrderStatus.CREATED, EnumSet.of(OrderStatus.PAYMENT_PENDING));
        map.put(OrderStatus.PAYMENT_PENDING, EnumSet.of(OrderStatus.PAID));
        map.put(OrderStatus.PAID, EnumSet.of(OrderStatus.PROCESSING));
        map.put(OrderStatus.PROCESSING, EnumSet.of(OrderStatus.SENT));
        map.put(OrderStatus.SENT, EnumSet.of(OrderStatus.DELIVERED));
        map.put(OrderStatus.DELIVERED, EnumSet.of(OrderStatus.RETURNED));
        map.put(OrderStatus.RETURNED, EnumSet.of(OrderStatus.SENT));
    }

    public OrderDTO updateOrderStatus(Long id, OrderStatus status){
        Order orderExists = findOrderOrThrow(id);
        Set<OrderStatus> allowed = map.get(orderExists.getOrderStatus());

        if(allowed != null && allowed.contains(status)){
            orderExists.setOrderStatus(status);
        } else {
            throw new FailedToUpdateOrderStatus("Cannot update order from " + orderExists.getOrderStatus() + " to " + status);
        }

        orderRepository.save(orderExists);
        return orderMapper.toDto(orderExists);
    }
}
