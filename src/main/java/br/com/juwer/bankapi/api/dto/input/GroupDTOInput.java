package br.com.juwer.bankapi.api.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record GroupDTOInput(
        @NotBlank String name,
        @NotNull Set<Long> permissions
) {}
