package br.com.leonardo.order_management_system.service;

import br.com.leonardo.order_management_system.entity.OrderItem;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderTotalValueCalculate {

    public BigDecimal calculateOrder(List<OrderItem> items, BigDecimal deliveryFee){
        BigDecimal orderTotalValue = deliveryFee;

        for(OrderItem i: items){
            BigDecimal price = i.getUnitPrice();
            int quantity = i.getQuantity();
            orderTotalValue = orderTotalValue.add(price.multiply(BigDecimal.valueOf(quantity)));
        }

        return orderTotalValue;
    }
}
