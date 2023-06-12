package br.com.juwer.bankapi.api.dto.input;

import jakarta.validation.constraints.NotBlank;

public record AddressDTOInput(
      @NotBlank String street,
      @NotBlank String city,
      @NotBlank String state,
      @NotBlank String postalCode,
      @NotBlank String country
) {}
