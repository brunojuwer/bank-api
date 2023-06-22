package br.com.juwer.bankapi.domain.exceptions;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(String message) {
        super(message);
    }

    public AccountNotFoundException(Long id) {
        this(String.format("Account with id %d not found", id));
    }
}
