package br.com.juwer.bankapi.config.security.dtoinput;

public record AuthenticationRequest(String email, String password) {
}
