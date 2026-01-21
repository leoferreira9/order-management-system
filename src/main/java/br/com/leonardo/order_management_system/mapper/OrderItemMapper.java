package br.com.leonardo.order_management_system.mapper;

import br.com.leonardo.order_management_system.dto.orderitem.OrderItemCreateDTO;
import br.com.leonardo.order_management_system.dto.orderitem.OrderItemDTO;
import br.com.leonardo.order_management_system.entity.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    OrderItemDTO toDto (OrderItem orderItem);

    OrderItem toEntity (OrderItemCreateDTO dto);
}
