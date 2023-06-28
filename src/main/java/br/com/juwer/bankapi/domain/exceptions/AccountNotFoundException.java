package br.com.juwer.bankapi.domain.exceptions;

public class AccountNotFoundException extends EntityNotFoundException {

    public AccountNotFoundException(String code) {
        super(String.format("Account with code %s not found", code));
    }
}
