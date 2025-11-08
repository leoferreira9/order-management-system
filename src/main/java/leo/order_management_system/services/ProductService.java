package leo.order_management_system.services;

import leo.order_management_system.dto.ProductDTO;
import leo.order_management_system.exceptions.EntityNotFound;
import leo.order_management_system.model.Product;
import leo.order_management_system.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductDTO create(Product product){
        productRepository.save(product);
        return new ProductDTO(product);
    }

    public ProductDTO findById(Long id){
        Product existingProduct = productRepository
                .findById(id).orElseThrow(() -> new EntityNotFound("Product not found with ID: " + id));
        return new ProductDTO(existingProduct);
    }

    public List<ProductDTO> findAll(){
        List<Product> products = productRepository.findAll();
        if(products.isEmpty()) throw new EntityNotFound("No products registered!");
        return products.stream().map(ProductDTO::new).toList();
    }

    public ProductDTO update(Product product){
        Product existingProduct = productRepository
                .findById(product.getId()).orElseThrow(() -> new EntityNotFound("Product not found with ID: " + product.getId()));

        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        productRepository.save(existingProduct);

        return new ProductDTO(existingProduct);
    }

    public void delete(Long id){
        Product product = productRepository
                .findById(id).orElseThrow(() -> new EntityNotFound("Product not found with ID: " + id));

        productRepository.delete(product);
    }
}

