package br.com.leonardo.order_management_system.dto.order;

import jakarta.validation.constraints.NotNull;

public class OrderUpdateDTO {

    @NotNull
    private Long addressId;

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }
}
