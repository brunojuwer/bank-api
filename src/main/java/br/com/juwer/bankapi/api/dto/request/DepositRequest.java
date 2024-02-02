package br.com.juwer.bankapi.api.dto.request;

import br.com.juwer.bankapi.config.validation.GreaterThanZero;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class DepositRequest {

    @NotNull
    @GreaterThanZero
    private BigDecimal amount;
}
