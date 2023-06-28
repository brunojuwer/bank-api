package br.com.juwer.bankapi.api.dto.output;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressDTO {

    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
}
