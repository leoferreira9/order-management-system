package br.com.leonardo.order_management_system.service;

import br.com.leonardo.order_management_system.dto.product.ProductCreateDTO;
import br.com.leonardo.order_management_system.dto.product.ProductDTO;
import br.com.leonardo.order_management_system.entity.Product;
import br.com.leonardo.order_management_system.exception.EntityNotFoundException;
import br.com.leonardo.order_management_system.mapper.ProductMapper;
import br.com.leonardo.order_management_system.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper){
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public Product findProductOrThrow(Long id){
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + id));
    }

    public ProductDTO create(ProductCreateDTO dto){
        Product product = productMapper.toEntity(dto);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDto(savedProduct);
    }

    public ProductDTO findById(Long id){
        Product product = findProductOrThrow(id);
        return productMapper.toDto(product);
    }

    public List<ProductDTO> findAll(){
        return productRepository.findAll().stream().map(productMapper::toDto).toList();
    }

    public ProductDTO update(Long id, ProductCreateDTO dto){
        Product productExists = findProductOrThrow(id);
        productExists.setName(dto.getName());
        productExists.setDescription(dto.getDescription());
        productExists.setPrice(dto.getPrice());

        Product updatedProduct = productRepository.save(productExists);

        return productMapper.toDto(updatedProduct);
    }

    public void delete(Long id){
        Product product = findProductOrThrow(id);
        productRepository.delete(product);
    }
}
