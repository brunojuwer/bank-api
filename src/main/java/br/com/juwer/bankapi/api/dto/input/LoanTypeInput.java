package br.com.juwer.bankapi.api.dto.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanTypeInput {

    @NotBlank
    private String name;

    @NotBlank
    private String description;
}
