package br.com.leonardo.order_management_system.mapper;

import br.com.leonardo.order_management_system.dto.address.AddressCreateDTO;
import br.com.leonardo.order_management_system.dto.address.AddressDTO;
import br.com.leonardo.order_management_system.entity.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressDTO toDto (Address address);

    Address toEntity (AddressCreateDTO dto);
}
