package br.com.juwer.bankapi.domain.exceptions;

public class InsuficientBalanceException extends RuntimeException {

    public InsuficientBalanceException(String message) {
        super(message);
    }
}
