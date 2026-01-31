package br.com.leonardo.order_management_system.controller;

import br.com.leonardo.order_management_system.dto.user.UserCreateDTO;
import br.com.leonardo.order_management_system.dto.user.UserDTO;
import br.com.leonardo.order_management_system.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User controller")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    @Operation(summary = "Create user", description = "Adds a new user")
    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody @Valid UserCreateDTO dto){
        return ResponseEntity.status(201).body(service.create(dto));
    }

    @Operation(summary = "Find user by ID", description = "Finds a user by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id){
        return ResponseEntity.status(200).body(service.findById(id));
    }

    @Operation(summary = "Find all users", description = "Find all saved users")
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        return ResponseEntity.status(200).body(service.findAll());
    }

    @Operation(summary = "Update user", description = "Updates a user")
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody @Valid UserCreateDTO dto){
        return ResponseEntity.status(200).body(service.update(id, dto));
    }

    @Operation(summary = "Delete user", description = "Deletes a user")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
