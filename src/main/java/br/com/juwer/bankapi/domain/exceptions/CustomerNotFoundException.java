package br.com.juwer.bankapi.domain.exceptions;

public class CustomerNotFoundException extends EntityNotFoundException {

    public CustomerNotFoundException(String message) {
        super(message);
    }

    public CustomerNotFoundException(Long userId) {
        this(String.format("Customer with id %d not found", userId));
    }
}
