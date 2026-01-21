package br.com.leonardo.order_management_system.controller;

import br.com.leonardo.order_management_system.dto.address.AddressCreateDTO;
import br.com.leonardo.order_management_system.dto.address.AddressDTO;
import br.com.leonardo.order_management_system.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService service;

    public AddressController(AddressService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AddressDTO> create(@RequestBody @Valid AddressCreateDTO dto){
        return ResponseEntity.status(201).body(service.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> findById(@PathVariable Long id){
        return ResponseEntity.status(200).body(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<AddressDTO>> findAll(){
        return ResponseEntity.status(200).body(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> update(@PathVariable Long id, @RequestBody @Valid AddressCreateDTO dto){
        return ResponseEntity.status(200).body(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
