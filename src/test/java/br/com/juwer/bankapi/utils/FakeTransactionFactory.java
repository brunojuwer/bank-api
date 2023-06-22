package br.com.juwer.bankapi.utils;

import br.com.juwer.bankapi.domain.model.Transaction;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class FakeTransactionFactory {
    public static Transaction createNewTransaction() {
        return Transaction.builder()
                .ammount(BigDecimal.valueOf(200.99))
                .operation(Transaction.Operation.DEPOSIT)
                .build();
    }

    public static Transaction createNewTransactionWithId() {
        return Transaction.builder()
                .id(1L)
                .ammount(BigDecimal.valueOf(200.99))
                .operation(Transaction.Operation.DEPOSIT)
                .createdAt(OffsetDateTime.now())
                .build();
    }
}
