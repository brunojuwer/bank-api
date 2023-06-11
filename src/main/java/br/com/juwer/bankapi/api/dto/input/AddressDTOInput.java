package br.com.juwer.bankapi.api.dto.input;

public record AddressDTOInput(
      String street,
      String city,
      String state,
      String postalCode,
      String country
) {}
