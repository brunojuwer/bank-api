package br.com.juwer.bankapi.api.dto.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanTypeInputId {

    @NotNull
    private Long id;
}
