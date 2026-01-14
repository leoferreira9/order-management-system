package br.com.leonardo.order_management_system.service;

import br.com.leonardo.order_management_system.dto.user.UserCreateDTO;
import br.com.leonardo.order_management_system.dto.user.UserDTO;
import br.com.leonardo.order_management_system.entity.User;
import br.com.leonardo.order_management_system.exception.EntityNotFoundException;
import br.com.leonardo.order_management_system.mapper.user.UserMapper;
import br.com.leonardo.order_management_system.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    private User findUserOrThrow(Long id){
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
    }

    public UserDTO create(UserCreateDTO dto){
        User user = userMapper.toEntity(dto);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    public UserDTO findById(Long id){
        User user = findUserOrThrow(id);
        return userMapper.toDto(user);
    }

    public List<UserDTO> findAll(){
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }

    public UserDTO update(Long id, UserCreateDTO dto){
        User userExists = findUserOrThrow(id);

        userExists.setBirthDate(dto.getBirthDate());
        userExists.setEmail(dto.getEmail());
        userExists.setFirstName(dto.getFirstName());
        userExists.setLastName(dto.getLastName());
        userExists.setPhoneNumber(dto.getPhoneNumber());

        userRepository.save(userExists);

        return userMapper.toDto(userExists);
    }

    public void delete(Long id){
        User userExists = findUserOrThrow(id);
        userRepository.delete(userExists);
    }
}
