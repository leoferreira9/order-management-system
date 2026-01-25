package br.com.leonardo.order_management_system.mapper;

import br.com.leonardo.order_management_system.dto.orderitem.OrderItemDTO;
import br.com.leonardo.order_management_system.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(source = "product.id", target = "productId")
    OrderItemDTO toDto (OrderItem orderItem);
}
