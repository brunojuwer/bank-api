package br.com.juwer.bankapi.api.dto.input;

import br.com.juwer.bankapi.domain.model.Transaction.Operation;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransactionDTOInput {

    @NotNull
    private Operation operation;

    @NotNull
    private BigDecimal amount;

}
