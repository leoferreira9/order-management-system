package leo.order_management_system.controllers;

import jakarta.validation.Valid;
import leo.order_management_system.dto.ProductDTO;
import leo.order_management_system.model.Product;
import leo.order_management_system.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ProductDTO create(@RequestBody @Valid Product product){
        return productService.create(product);
    }

    @GetMapping("/{id}")
    public ProductDTO findById(@PathVariable Long id){
        return productService.findById(id);
    }

    @GetMapping
    public List<ProductDTO> findAll(){
        return productService.findAll();
    }

    @PutMapping
    public ProductDTO update(@RequestBody @Valid Product product){
        return productService.update(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
