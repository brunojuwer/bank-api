package br.com.juwer.bankapi.domain.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Long userId) {
        this(String.format("User with id %d not found", userId));
    }
}
