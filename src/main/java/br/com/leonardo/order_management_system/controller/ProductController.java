package br.com.leonardo.order_management_system.controller;

import br.com.leonardo.order_management_system.dto.product.ProductCreateDTO;
import br.com.leonardo.order_management_system.dto.product.ProductDTO;
import br.com.leonardo.order_management_system.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Product controller")
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service){
        this.service = service;
    }

    @Operation(summary = "Create product", description = "Adds a new product")
    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody @Valid ProductCreateDTO dto){
        return ResponseEntity.status(201).body(service.create(dto));
    }

    @Operation(summary = "Find product by ID", description = "Finds a product by it's ID")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id){
        return ResponseEntity.status(200).body(service.findById(id));
    }

    @Operation(summary = "Find all products", description = "Find all saved products")
    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll(){
        return ResponseEntity.status(200).body(service.findAll());
    }

    @Operation(summary = "Update product", description = "Updates a product")
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody @Valid ProductCreateDTO dto){
        return ResponseEntity.status(200).body(service.update(id, dto));
    }

    @Operation(summary = "Delete product", description = "Deletes a product")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
