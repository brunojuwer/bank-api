package br.com.juwer.bankapi.api.dto.output;

import br.com.juwer.bankapi.domain.model.Transaction.Operation;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class TransactionDTO {
    private Long transactionId;
    private String accountCode;
    private BigDecimal amount;
    private String product;
    private Operation operation;
    private OffsetDateTime createdAt;
}
