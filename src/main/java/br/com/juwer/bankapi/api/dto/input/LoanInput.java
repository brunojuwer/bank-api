package br.com.juwer.bankapi.api.dto.input;

import br.com.juwer.bankapi.config.validation.GreaterThanZero;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class LoanInput {

    @GreaterThanZero
    private BigDecimal requiredAmount;

    @NotBlank
    private String description;

    @Valid
    private LoanTypeInputId loanType;
}
