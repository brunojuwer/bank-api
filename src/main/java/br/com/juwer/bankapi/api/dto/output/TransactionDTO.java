package br.com.juwer.bankapi.api.dto.output;

import br.com.juwer.bankapi.domain.model.Transaction.Operation;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TransactionDTO(
        Long transactionId,
        BigDecimal ammount,
        Operation operation,
        OffsetDateTime createdAt
) {}
