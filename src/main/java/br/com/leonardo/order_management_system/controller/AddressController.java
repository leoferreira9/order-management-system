package br.com.leonardo.order_management_system.controller;

import br.com.leonardo.order_management_system.dto.address.AddressCreateDTO;
import br.com.leonardo.order_management_system.dto.address.AddressDTO;
import br.com.leonardo.order_management_system.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Address controller")
@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService service;

    public AddressController(AddressService service){
        this.service = service;
    }

    @Operation(summary = "Create address", description = "Adds a new address")
    @PostMapping
    public ResponseEntity<AddressDTO> create(@RequestBody @Valid AddressCreateDTO dto){
        return ResponseEntity.status(201).body(service.create(dto));
    }

    @Operation(summary = "Find address by ID", description = "Finds a address by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> findById(@PathVariable Long id){
        return ResponseEntity.status(200).body(service.findById(id));
    }

    @Operation(summary = "Find all addresses", description = "Find all saved addresses")
    @GetMapping
    public ResponseEntity<List<AddressDTO>> findAll(){
        return ResponseEntity.status(200).body(service.findAll());
    }

    @Operation(summary = "Update address", description = "Updates an address")
    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> update(@PathVariable Long id, @RequestBody @Valid AddressCreateDTO dto){
        return ResponseEntity.status(200).body(service.update(id, dto));
    }

    @Operation(summary = "Delete address", description = "Deletes an address")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
