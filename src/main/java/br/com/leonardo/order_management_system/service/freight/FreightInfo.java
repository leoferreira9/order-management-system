package br.com.leonardo.order_management_system.service.freight;

import br.com.leonardo.order_management_system.enums.DeliveryType;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FreightInfo(BigDecimal deliveryFee, LocalDate deliveryDate, DeliveryType deliveryType) {}
