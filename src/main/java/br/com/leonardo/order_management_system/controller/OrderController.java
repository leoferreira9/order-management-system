package br.com.leonardo.order_management_system.controller;

import br.com.leonardo.order_management_system.dto.order.OrderCreateDTO;
import br.com.leonardo.order_management_system.dto.order.OrderDTO;
import br.com.leonardo.order_management_system.dto.order.OrderUpdateDTO;
import br.com.leonardo.order_management_system.enums.OrderStatus;
import br.com.leonardo.order_management_system.service.OrderService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<OrderDTO> create(@RequestBody @Valid OrderCreateDTO dto){
        return ResponseEntity.status(201).body(service.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable Long id){
        return ResponseEntity.status(200).body(service.findById(id));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<OrderDTO>> findAllByUserId(@PathVariable Long id){
        return ResponseEntity.status(200).body(service.findAllByUserId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> update(@PathVariable Long id, @RequestBody @Valid OrderUpdateDTO dto){
        return ResponseEntity.status(200).body(service.update(id, dto));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable Long id){
        service.cancel(id);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping("/{id}/refund")
    public ResponseEntity<Void> refund(@PathVariable Long id){
        service.refund(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/updateStatus")
    public ResponseEntity<OrderDTO> updateStatus(@PathVariable Long id, @RequestBody OrderStatus orderStatus){
        return ResponseEntity.status(200).body(service.updateOrderStatus(id, orderStatus));
    }
}
