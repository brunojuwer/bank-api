package br.com.juwer.bankapi.utils;

import br.com.juwer.bankapi.domain.model.Account;

import java.math.BigDecimal;

public class FakeAccountFactory {

    public static Account createAccount() {
        return Account.builder()
                .type(Account.Type.CURRENT)
                .balance(BigDecimal.ZERO)
                .build();
    }

    public static Account createAccountWithId() {
        return Account.builder()
                .id(1L)
                .type(Account.Type.CURRENT)
                .balance(BigDecimal.ZERO)
                .build();
    }

    public static Account createAccountWithIdAndNewBalance() {
        return Account.builder()
                .id(1L)
                .type(Account.Type.CURRENT)
                .balance(BigDecimal.valueOf(200.99))
                .build();
    }
}
