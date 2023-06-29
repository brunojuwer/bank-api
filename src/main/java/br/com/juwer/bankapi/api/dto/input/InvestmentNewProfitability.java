package br.com.juwer.bankapi.api.dto.input;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class InvestmentNewProfitability {

    @NotNull
    @Positive
    private BigDecimal profitability;
}
