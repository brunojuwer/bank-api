package br.com.juwer.bankapi.config.security.dtoinput;

public record AuthenticationRequest(String code, String password) {
}
