package leo.order_management_system.controllers;

import jakarta.validation.Valid;
import leo.order_management_system.dto.OrderDTO;
import leo.order_management_system.model.Order;
import leo.order_management_system.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public OrderDTO create(@RequestBody @Valid Order order){
        return orderService.create(order);
    }

    @GetMapping("/{id}")
    public OrderDTO findById(@PathVariable Long id){
        return orderService.findById(id);
    }

    @GetMapping
    public List<OrderDTO> findAll(){
        return orderService.findAll();
    }

    @PutMapping
    public OrderDTO update(@RequestBody @Valid Order order){
        return orderService.update(order);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
