package br.com.juwer.bankapi.api.dto.output;

public record UserDTO(
        Long id,
        String fullName,
        String email
) {}
