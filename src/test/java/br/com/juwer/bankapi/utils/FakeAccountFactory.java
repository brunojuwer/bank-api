package br.com.juwer.bankapi.utils;

import br.com.juwer.bankapi.domain.model.Account;

import java.math.BigDecimal;

public class FakeAccountFactory {

    public static Account createAccount() {
        return Account.builder()
                .type(Account.Type.PF)
                .balance(BigDecimal.ZERO)
                .build();
    }

    public static Account createAccountWithId() {
        return Account.builder()
                .code("89575640")
                .type(Account.Type.PF)
                .balance(BigDecimal.ZERO)
                .build();
    }

    public static Account createAccountWithIdAndNewBalance() {
        return Account.builder()
                .code("89575640")
                .type(Account.Type.PF)
                .balance(BigDecimal.valueOf(200.99))
                .build();
    }
}
