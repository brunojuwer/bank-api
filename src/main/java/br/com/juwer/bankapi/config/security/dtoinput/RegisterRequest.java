package br.com.juwer.bankapi.config.security.dtoinput;

public record RegisterRequest(
        String fullName,
        String email,
        String password
    ) {
}
