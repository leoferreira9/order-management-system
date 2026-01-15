package br.com.leonardo.order_management_system.service;

import br.com.leonardo.order_management_system.dto.address.AddressCreateDTO;
import br.com.leonardo.order_management_system.dto.address.AddressDTO;
import br.com.leonardo.order_management_system.entity.Address;
import br.com.leonardo.order_management_system.exception.EntityNotFoundException;
import br.com.leonardo.order_management_system.mapper.address.AddressMapper;
import br.com.leonardo.order_management_system.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressService(AddressRepository addressRepository, AddressMapper addressMapper){
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    private Address findAddressOrThrow(Long id){
        return addressRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Address not found with ID: " + id));
    }

    public AddressDTO create(AddressCreateDTO dto){
        Address address = addressMapper.toEntity(dto);
        Address savedAddress = addressRepository.save(address);
        return addressMapper.toDto(savedAddress);
    }

    public AddressDTO findById(Long id){
        Address addressExists = findAddressOrThrow(id);
        return addressMapper.toDto(addressExists);
    }

    public List<AddressDTO> findAll(){
        return addressRepository.findAll().stream().map(addressMapper::toDto).toList();
    }

    public AddressDTO update(Long id, AddressCreateDTO dto){
        Address addressExists = findAddressOrThrow(id);

        addressExists.setCity(dto.getCity());
        addressExists.setComplement(dto.getComplement());
        addressExists.setNeighborhood(dto.getNeighborhood());
        addressExists.setNumber(dto.getNumber());
        addressExists.setState(dto.getState());
        addressExists.setStreet(dto.getStreet());
        addressExists.setPostalCode(dto.getPostalCode());

        Address savedAddress = addressRepository.save(addressExists);

        return addressMapper.toDto(savedAddress);
    }

    public void delete(Long id){
        Address addressExists = findAddressOrThrow(id);
        addressRepository.delete(addressExists);
    }
}
