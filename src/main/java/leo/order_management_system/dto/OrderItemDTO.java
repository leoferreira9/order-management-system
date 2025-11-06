package leo.order_management_system.dto;

public class OrderItemDTO {

    private Long id;
    private int quantity;
    private Double price;
    private ProductDTO productDTO;

    public OrderItemDTO(){}

    public OrderItemDTO(Long id, int quantity, Double price, ProductDTO productDTO) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.productDTO = productDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

}
