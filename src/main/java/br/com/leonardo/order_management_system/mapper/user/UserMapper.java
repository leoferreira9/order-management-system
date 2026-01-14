package br.com.leonardo.order_management_system.mapper.user;

import br.com.leonardo.order_management_system.dto.user.UserDTO;
import br.com.leonardo.order_management_system.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDto (User user);

    User toEntity(UserDTO dto);
}
