package br.com.juwer.bankapi.domain.exceptions;

public class TransactionNotFoundException extends RuntimeException {

    public TransactionNotFoundException(String message) {
        super(message);
    }

    public TransactionNotFoundException(Long id) {
        this(String.format("Transaction with id %d not found", id));
    }
}
