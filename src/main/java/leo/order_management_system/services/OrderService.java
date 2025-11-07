package leo.order_management_system.services;

import leo.order_management_system.dto.OrderDTO;
import leo.order_management_system.model.Order;
import leo.order_management_system.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderDTO create(Order order){
        order.setDate(LocalDateTime.now());
        orderRepository.save(order);
        return new OrderDTO(order);
    }

}
