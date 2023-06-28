package br.com.juwer.bankapi.domain.exceptions;

public class ExistingEntityException extends RuntimeException {
    public ExistingEntityException(String message) {
        super(message);
    }
}
