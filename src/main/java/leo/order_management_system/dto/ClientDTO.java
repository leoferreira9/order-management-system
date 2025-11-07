package leo.order_management_system.dto;

import leo.order_management_system.model.Client;

public class ClientDTO {

    private String name;
    private String email;

    public ClientDTO(){}

    public ClientDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public ClientDTO(Client client) {
        this.name = client.getName();
        this.email = client.getEmail();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
