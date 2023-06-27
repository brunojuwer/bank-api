package br.com.juwer.bankapi.api.dto.input;

import jakarta.validation.constraints.NotBlank;

public record CustomerDTOInput(
        @NotBlank String fullName,
        @NotBlank String email,
        @NotBlank String password
) {}
