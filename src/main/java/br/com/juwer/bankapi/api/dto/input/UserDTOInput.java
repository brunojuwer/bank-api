package br.com.juwer.bankapi.api.dto.input;

import jakarta.validation.constraints.NotBlank;

public record UserDTOInput(
        @NotBlank String fullName,
        @NotBlank String email,
        @NotBlank String password
) {}
