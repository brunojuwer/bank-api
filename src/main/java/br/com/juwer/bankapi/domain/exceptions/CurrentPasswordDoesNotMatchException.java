package br.com.juwer.bankapi.domain.exceptions;

public class CurrentPasswordDoesNotMatchException extends RuntimeException {
    public CurrentPasswordDoesNotMatchException(String message) {
        super(message);
    }
}
