package br.com.juwer.bankapi.domain.exceptions;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(String code) {
        super(String.format("Account with id %s not found", code));
    }
}
