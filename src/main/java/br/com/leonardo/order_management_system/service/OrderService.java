package br.com.leonardo.order_management_system.service;

import br.com.leonardo.order_management_system.dto.order.OrderCreateDTO;
import br.com.leonardo.order_management_system.dto.order.OrderDTO;
import br.com.leonardo.order_management_system.dto.order.OrderUpdateDTO;
import br.com.leonardo.order_management_system.entity.Address;
import br.com.leonardo.order_management_system.entity.Order;
import br.com.leonardo.order_management_system.entity.User;
import br.com.leonardo.order_management_system.enums.DeliveryType;
import br.com.leonardo.order_management_system.enums.OrderStatus;
import br.com.leonardo.order_management_system.exception.EntityNotFoundException;
import br.com.leonardo.order_management_system.mapper.OrderMapper;
import br.com.leonardo.order_management_system.repository.AddressRepository;
import br.com.leonardo.order_management_system.repository.OrderRepository;
import br.com.leonardo.order_management_system.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, AddressRepository addressRepository, OrderMapper orderMapper){
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.orderMapper = orderMapper;
    }

    public Order findOrderOrThrow(Long id){
        return orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found with ID: " + id));
    }

    //“V1 – regras temporárias, será refatorado”

    public OrderDTO create(OrderCreateDTO dto){
        Order order = orderMapper.toEntity(dto);

        UUID uuid = UUID.randomUUID();
        Address address = addressRepository.findById(dto.getAddressId()).orElseThrow(() -> new EntityNotFoundException("Address not found with ID: " + dto.getAddressId()));
        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + dto.getUserId()));
        LocalDateTime dateNow = LocalDateTime.now();
        LocalDate deliveryDate = dto.getDeliveryFee().compareTo(new BigDecimal(25)) < 0 ? LocalDate.now().plusDays(5) : LocalDate.now().plusDays(2);
        DeliveryType deliveryType = dto.getDeliveryFee().compareTo(new BigDecimal(25)) < 0 ? DeliveryType.NORMAL : DeliveryType.EXPRESS;
        String orderNumber = "ORD-" + uuid.toString().toUpperCase().substring(30, 36);


        order.setOrderNumber(orderNumber);
        order.setOrderDate(dateNow);
        order.setDeliveryDate(deliveryDate);
        order.setDeliveryType(deliveryType);
        order.setOrderStatus(OrderStatus.CREATED);
        order.setAddress(address);
        order.setUser(user);
        order.setTotalValue(new BigDecimal(100));

        Order savedOrder = orderRepository.save(order);
        return orderMapper.toDto(savedOrder);
    }

    public OrderDTO findById(Long id){
        Order order = findOrderOrThrow(id);
        return orderMapper.toDto(order);
    }

    public OrderDTO update(Long id, OrderUpdateDTO dto){
        Order orderExists = findOrderOrThrow(id);
        Address address = addressRepository.findById(dto.getAddressId()).orElseThrow(() -> new EntityNotFoundException("Address not found with ID: " + dto.getAddressId()));

        orderExists.setAddress(address);
        Order savedOrder = orderRepository.save(orderExists);

        return orderMapper.toDto(savedOrder);
    }

}
