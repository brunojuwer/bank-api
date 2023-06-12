package br.com.juwer.bankapi.api.dto.input;

import jakarta.validation.constraints.NotBlank;

public record PermissionDTOInput(
        @NotBlank String name,
        @NotBlank String description
) {
}
