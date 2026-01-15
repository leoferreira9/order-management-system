package br.com.leonardo.order_management_system.dto.product;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class ProductCreateDTO {

    @NotBlank
    @Size(max = 150)
    private String name;

    @NotNull
    @DecimalMin("0.0")
    @Digits(integer = 7, fraction = 2)
    private BigDecimal price;

    @Size(max = 250)
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
