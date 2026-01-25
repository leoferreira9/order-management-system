package br.com.leonardo.order_management_system.dto.order;

import br.com.leonardo.order_management_system.dto.orderitem.OrderItemCreateDTO;
import br.com.leonardo.order_management_system.enums.DeliveryType;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

public class OrderCreateDTO {

    @NotNull
    private DeliveryType deliveryType;

    @NotNull
    private Long userId;

    @NotNull
    private Long addressId;

    @NotNull
    private List<OrderItemCreateDTO> items = new ArrayList<>();

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
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

    public List<OrderItemCreateDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemCreateDTO> items) {
        this.items = items;
    }
}
