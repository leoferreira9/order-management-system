package br.com.leonardo.order_management_system.service.freight;

import br.com.leonardo.order_management_system.enums.DeliveryType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class FreightService {

    public FreightInfo calculateFreight(DeliveryType deliveryType, LocalDate orderDate){
        BigDecimal deliveryFee = deliveryType.equals(DeliveryType.EXPRESS) ? new BigDecimal("30.00") : new BigDecimal("15.00");
        LocalDate deliveryDate = deliveryType.equals(DeliveryType.EXPRESS) ? orderDate.plusDays(2) : orderDate.plusDays(5);
        return new FreightInfo(deliveryFee, deliveryDate, deliveryType);
    }
}
