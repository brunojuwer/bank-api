package br.com.juwer.bankapi.domain.exceptions;

public class AccountInvestmentNotFoundException extends EntityNotFoundException {
    public AccountInvestmentNotFoundException(String message) {
        super(message);
    }
}
