package br.com.leonardo.order_management_system.mapper;

import br.com.leonardo.order_management_system.dto.product.ProductCreateDTO;
import br.com.leonardo.order_management_system.dto.product.ProductDTO;
import br.com.leonardo.order_management_system.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO toDto (Product product);

    Product toEntity (ProductCreateDTO dto);
}
