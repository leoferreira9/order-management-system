package br.com.leonardo.order_management_system.dto.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AddressCreateDTO {

    @NotBlank
    @Size(max = 150)
    private String street;

    @NotBlank
    @Size(max = 7)
    private String number;

    @NotBlank
    @Size(max = 100)
    private String neighborhood;

    @NotBlank
    @Size(max = 100)
    private String city;

    @NotBlank
    @Size(max = 2)
    private String state;

    @Size(max = 150)
    private String complement;

    @NotBlank
    @Size(max = 9)
    @Pattern(regexp = "\\d{5}-\\d{3}")
    private String postalCode;

}
