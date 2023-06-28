package br.com.juwer.bankapi.api.dto.input;

import br.com.juwer.bankapi.domain.model.Account.Type;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountInput {

    @NotBlank
    private String password;

    @NotNull
    private Type type;

    @NotNull
    private BigDecimal balance;

    @Valid
    private CustomerInput customer;

}
