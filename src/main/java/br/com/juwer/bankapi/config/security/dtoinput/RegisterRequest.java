package br.com.juwer.bankapi.config.security.dtoinput;

public record RegisterRequest(
        String firstname,
        String lastname,
        String email,
        String password
    ) {
}
