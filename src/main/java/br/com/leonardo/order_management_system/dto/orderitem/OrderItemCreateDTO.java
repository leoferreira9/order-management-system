package br.com.leonardo.order_management_system.dto.orderitem;

import jakarta.validation.constraints.*;

public class OrderItemCreateDTO {

    @NotNull
    private Long productId;

    @NotNull
    @Min(1)
    private int quantity;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
