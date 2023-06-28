package br.com.juwer.bankapi.config.security.verification;


import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public @interface CheckSecurity {


    @interface Accounts {

        @PreAuthorize("@securityUtils.verifyIfAccountMatches(#accountCode)")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanMakeTransfer {}

        @PreAuthorize(value = "hasAuthority('SCOPE_READ') and isAuthenticated()")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanMakeConsult {}
    }
}
