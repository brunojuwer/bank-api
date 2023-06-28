package br.com.juwer.bankapi.api.dto.output;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Setter
@Getter
public class CustomerDTO {
    private Long id;
    private String fullName;
    private String cpf;
    private String email;
    private OffsetDateTime birthdate;
    private String contactNumber;
    private String nationality;
    private AddressDTO address;
}
