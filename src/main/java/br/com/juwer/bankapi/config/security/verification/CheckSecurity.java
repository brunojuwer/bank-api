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

        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanConsult {}

        @PreAuthorize("@securityUtils.verifyIfAccountMatches(#accountCode)")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface OwsAccount {}
    }

    @interface Customers {

        @PreAuthorize("@securityUtils.verifyIfCustomerIdMatches(#customerId)")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanConsult {}

        @PreAuthorize("(isAuthenticated() and " +
                " @securityUtils.verifyIfCustomerIdMatches(#customerId)")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanUpdate {}

//        @PreAuthorize("(isAuthenticated())
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanEdit {}
    }

}
