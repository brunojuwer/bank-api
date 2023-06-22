package br.com.juwer.bankapi.api.dto.input;

import br.com.juwer.bankapi.domain.model.Transaction.Operation;

import java.math.BigDecimal;

public record TransactionDTOInput(
   Operation operation,
   BigDecimal ammount
) {}
