package leo.order_management_system.services;

import leo.order_management_system.dto.ClientDTO;
import leo.order_management_system.exceptions.ClientAlreadyRegistered;
import leo.order_management_system.exceptions.EntityNotFound;
import leo.order_management_system.model.Client;
import leo.order_management_system.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public ClientDTO create(Client client){
        if(clientRepository.findByEmail(client.getEmail()).isPresent()) throw new ClientAlreadyRegistered("Email already registered!");
        clientRepository.save(client);
        return new ClientDTO(client);

    }

    public ClientDTO findById(Long id){
        Client client = clientRepository.findById(id).orElseThrow(() -> new EntityNotFound("Client not found with ID: " + id));
        return new ClientDTO(client);
    }

    public List<ClientDTO> findAll(){
        List<Client> clients = clientRepository.findAll();
        if(clients.isEmpty()) throw new EntityNotFound("No clients registered");
        return clients.stream().map(ClientDTO::new).toList();
    }

    public ClientDTO update(Client client){
        Client existingClient = clientRepository.findById(client.getId()).orElseThrow(() -> new EntityNotFound("Client not found with ID: " + client.getId()));
        if(!existingClient.getEmail().equals(client.getEmail()) && clientRepository.findByEmail(client.getEmail()).isPresent()) throw new ClientAlreadyRegistered("Email already registered!");

        existingClient.setName(client.getName());
        existingClient.setEmail(client.getEmail());
        clientRepository.save(existingClient);
        return new ClientDTO(client);
    }

    public void delete(Long id){
        Client client = clientRepository.findById(id).orElseThrow(() -> new EntityNotFound("Client not found with ID: " + id));
        clientRepository.delete(client);
    }
}