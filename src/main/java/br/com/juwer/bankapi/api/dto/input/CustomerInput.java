package br.com.juwer.bankapi.api.dto.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class CustomerInput {

    @NotBlank
    private String fullName;

    @NotBlank
    private String cpf;

    @Email
    private String email;

    @NotNull
    private OffsetDateTime birthdate;

    @NotBlank
    private String contactNumber;

    @NotBlank
    private String nationality;

    @Valid
    private AddressDTOInput address;
}
