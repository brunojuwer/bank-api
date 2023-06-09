package br.com.juwer.bankapi.api.dto.input;

public record UserDTOInput(
        String fullName,
        String email,
        String password
) {}
