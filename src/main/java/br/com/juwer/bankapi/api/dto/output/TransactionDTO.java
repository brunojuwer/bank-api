package br.com.juwer.bankapi.api.dto.output;

import br.com.juwer.bankapi.domain.model.Transaction.Operation;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TransactionDTO(
        Long transactionId,
        String accountCode,
        BigDecimal amount,
        Operation operation,
        OffsetDateTime createdAt
) {}
