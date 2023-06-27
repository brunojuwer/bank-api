package br.com.juwer.bankapi.utils;

import br.com.juwer.bankapi.domain.model.Customer;

public class FakeUserFactory {

    public static Customer generateSimpleUserWithIdAndEncodedPassword() {
        return Customer.builder()
                .id(1L)
                .email("email@email.com")
//                .password("$2a$10$BNkOUm6tdcy.I8Vu5ll5VOx7y9k.uI9FcED3jlyBdWCYsi7Zjlsji")
                .fullName("Fulano da Silva")
                .build();
    }

    public static Customer generateSimpleUser() {
        return Customer.builder()
                .email("email@email.com")
//                .password("1234")
                .fullName("Fulano da Silva")
                .build();
    }

    public static Customer generateSimpleUserWithId() {
        return Customer.builder()
                .id(1L)
                .email("email@email.com")
//                .password("1234")
                .fullName("Fulano da Silva")
                .build();
    }
}
