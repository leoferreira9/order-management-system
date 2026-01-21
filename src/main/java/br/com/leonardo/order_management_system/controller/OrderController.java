package br.com.leonardo.order_management_system.controller;

import br.com.leonardo.order_management_system.dto.order.OrderCreateDTO;
import br.com.leonardo.order_management_system.dto.order.OrderDTO;
import br.com.leonardo.order_management_system.dto.order.OrderUpdateDTO;
import br.com.leonardo.order_management_system.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> update(@PathVariable Long id, @RequestBody @Valid OrderUpdateDTO dto){
        return ResponseEntity.status(200).body(service.update(id, dto));
    }
}
