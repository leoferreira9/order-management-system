package leo.order_management_system.services;

import leo.order_management_system.dto.OrderDTO;
import leo.order_management_system.exceptions.EntityNotFound;
import leo.order_management_system.model.Client;
import leo.order_management_system.model.Order;
import leo.order_management_system.model.Product;
import leo.order_management_system.repository.ClientRepository;
import leo.order_management_system.repository.OrderRepository;
import leo.order_management_system.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    public OrderDTO create(Order order){
        order.setDate(LocalDateTime.now());

        Client client = clientRepository.findById(order.getClient().getId())
                .orElseThrow(() -> new EntityNotFound("Client not found with ID: " + order.getClient().getId()));

        order.setClient(client);

        order.getItems().forEach(item -> {
            Product product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new EntityNotFound("Product not found with ID: " + item.getProduct().getId()));

            item.setProduct(product);
            item.setOrder(order);
        });

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

        Client client = clientRepository
                .findById(order.getClient().getId()).orElseThrow(() -> new EntityNotFound("Client not found with ID: " + order.getClient().getId()));

        existingOrder.setClient(client);
        existingOrder.setDate(order.getDate());

        existingOrder.getItems().clear();

        order.getItems().forEach(item -> {
            item.setOrder(existingOrder);
            existingOrder.getItems().add(item);
        });

        orderRepository.save(existingOrder);

        return new OrderDTO(existingOrder);
    }

    public void delete(Long id){
        Order order = orderRepository
                .findById(id).orElseThrow(() -> new EntityNotFound("Order not found with ID: " + id));
        orderRepository.delete(order);
    }
}
