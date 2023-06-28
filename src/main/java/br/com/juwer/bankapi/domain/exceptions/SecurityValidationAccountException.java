package br.com.juwer.bankapi.domain.exceptions;

public class SecurityValidationAccountException extends RuntimeException {
    public SecurityValidationAccountException(String message) {
        super(message);
    }
}
