package leo.order_management_system.controllers;

import jakarta.validation.Valid;
import leo.order_management_system.dto.ClientDTO;
import leo.order_management_system.model.Client;
import leo.order_management_system.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ClientDTO create(@RequestBody @Valid Client client){
        return clientService.create(client);
    }

    @GetMapping("/{id}")
    public ClientDTO findById(@PathVariable(name = "id") Long id){
        return clientService.findById(id);
    }

    @GetMapping
    public List<ClientDTO> findAll(){
        return clientService.findAll();
    }

    @PutMapping
    public ClientDTO update(@RequestBody @Valid Client client){
        return clientService.update(client);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id){
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
