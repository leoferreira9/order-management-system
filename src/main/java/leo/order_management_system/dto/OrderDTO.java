package leo.order_management_system.dto;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {


    private Long id;
    private LocalDateTime date;
    private ClientDTO clientDTO;
    private List<OrderItemDTO> itemsDTO;


    public OrderDTO(){}

    public OrderDTO(Long id, LocalDateTime date, ClientDTO clientDTO, List<OrderItemDTO> itemsDTO) {
        this.id = id;
        this.date = date;
        this.clientDTO = clientDTO;
        this.itemsDTO = itemsDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public ClientDTO getClientDTO() {
        return clientDTO;
    }

    public void setClientDTO(ClientDTO clientDTO) {
        this.clientDTO = clientDTO;
    }

    public List<OrderItemDTO> getItemsDTO() {
        return itemsDTO;
    }

    public void setItemsDTO(List<OrderItemDTO> itemsDTO) {
        this.itemsDTO = itemsDTO;
    }
}
