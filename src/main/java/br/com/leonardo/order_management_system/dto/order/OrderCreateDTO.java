package br.com.leonardo.order_management_system.dto.order;

import br.com.leonardo.order_management_system.enums.DeliveryType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class OrderCreateDTO {

    @NotNull
    private DeliveryType deliveryType;

    @NotNull
    @DecimalMin("0.0")
    @Digits(integer = 5, fraction = 2)
    private BigDecimal deliveryFee;

    @NotNull
    private Long userId;

    @NotNull
    private Long addressId;

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public BigDecimal getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(BigDecimal deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }
}
