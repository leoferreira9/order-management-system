package br.com.leonardo.order_management_system.controller;

import br.com.leonardo.order_management_system.dto.order.OrderCreateDTO;
import br.com.leonardo.order_management_system.dto.order.OrderDTO;
import br.com.leonardo.order_management_system.dto.order.OrderUpdateDTO;
import br.com.leonardo.order_management_system.enums.OrderStatus;
import br.com.leonardo.order_management_system.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Order controller")
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service){
        this.service = service;
    }

    @Operation(summary = "Create order", description = "Adds a new order")
    @PostMapping
    public ResponseEntity<OrderDTO> create(@RequestBody @Valid OrderCreateDTO dto){
        return ResponseEntity.status(201).body(service.create(dto));
    }

    @Operation(summary = "Find order by ID", description = "Finds a order by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable Long id){
        return ResponseEntity.status(200).body(service.findById(id));
    }

    @Operation(summary = "Find all orders", description = "Find all saved orders")
    @GetMapping("/user/{id}")
    public ResponseEntity<List<OrderDTO>> findAllByUserId(@PathVariable Long id){
        return ResponseEntity.status(200).body(service.findAllByUserId(id));
    }

    @Operation(summary = "Update order", description = "Updates an order")
    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> update(@PathVariable Long id, @RequestBody @Valid OrderUpdateDTO dto){
        return ResponseEntity.status(200).body(service.update(id, dto));
    }

    @Operation(summary = "Cancel order", description = "Cancel an existing order")
    @PutMapping("/{id}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable Long id){
        service.cancel(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Refund order", description = "Refunds an existing order")
    @Transactional
    @PutMapping("/{id}/refund")
    public ResponseEntity<Void> refund(@PathVariable Long id){
        service.refund(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update order status", description = "Updates the status of an order")
    @PutMapping("/{id}/updateStatus")
    public ResponseEntity<OrderDTO> updateStatus(@PathVariable Long id, @RequestBody OrderStatus orderStatus){
        return ResponseEntity.status(200).body(service.updateOrderStatus(id, orderStatus));
    }
}
