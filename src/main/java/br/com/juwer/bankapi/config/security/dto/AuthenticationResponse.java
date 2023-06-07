package br.com.juwer.bankapi.config.security.dto;

public record AuthenticationResponse(String token, String username, Long userId) {}
