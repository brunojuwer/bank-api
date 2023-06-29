package br.com.juwer.bankapi.api.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class InvestmentInput {

    @NotBlank
    private String name;

    @NotNull
    private BigDecimal profitability;

    @NotBlank
    private String description;
}
