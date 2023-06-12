package br.com.juwer.bankapi.config.security.dto;

public record FailedToGenerateToken(int status, String title, String detail) {
}
