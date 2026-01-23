package br.com.leonardo.order_management_system.dto.order;

import br.com.leonardo.order_management_system.enums.DeliveryType;

public class OrderUpdateDTO {

    private Long addressId;

    private DeliveryType deliveryType;

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }
}
