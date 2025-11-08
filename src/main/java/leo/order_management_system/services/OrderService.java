package leo.order_management_system.services;

import leo.order_management_system.dto.OrderDTO;
import leo.order_management_system.exceptions.EntityNotFound;
import leo.order_management_system.model.Order;
import leo.order_management_system.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderDTO create(Order order){
        order.setDate(LocalDateTime.now());
        orderRepository.save(order);
        return new OrderDTO(order);
    }

    public OrderDTO findById(Long id){
        Order order = orderRepository
                .findById(id).orElseThrow(() -> new EntityNotFound("Order not found with ID: " + id));
        return new OrderDTO(order);
    }

    public List<OrderDTO> findAll(){
        List<Order> orders = orderRepository.findAll();
        if(orders.isEmpty()) throw new EntityNotFound("No orders registered!");
        return orders.stream().map(OrderDTO::new).toList();
    }

    public OrderDTO update(Order order){
        Order existingOrder = orderRepository
                .findById(order.getId()).orElseThrow(() -> new EntityNotFound("Order not found with ID:" + order.getId()));
        existingOrder.setClient(order.getClient());
        existingOrder.setDate(order.getDate());
        existingOrder.setItems(order.getItems());
        orderRepository.save(existingOrder);

        return new OrderDTO(existingOrder);
    }

    public void delete(Long id){
        Order order = orderRepository
                .findById(id).orElseThrow(() -> new EntityNotFound("Order not found with ID: " + id));
        orderRepository.delete(order);
    }
}
