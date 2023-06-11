package br.com.juwer.bankapi.api.dto.output;

public record AddressDTO(
        Long id,
        String street,
        String city,
        String state,
        String postalCode,
        String country
) {}
