package br.com.juwer.bankapi.config.security.verification;

import br.com.juwer.bankapi.domain.exceptions.SecurityValidationAccountException;
import br.com.juwer.bankapi.domain.model.Account;
import br.com.juwer.bankapi.domain.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Component
@RequiredArgsConstructor
public class SecurityUtils {

    private final AccountService accountService;

    public boolean verifyIfAccountMatches(String accountCode) {
        Account authenticatedAccount = (Account) getContext().getAuthentication().getPrincipal();

        if(!accountCode.equals(authenticatedAccount.getCode())) {
            throw new SecurityValidationAccountException("There is a security failure validation");
        }
        return true;
    }

    public boolean verifyIfCustomerIdMatches(Long customerId) {
        Account authenticatedAccount = (Account) getContext().getAuthentication().getPrincipal();

        if(!customerId.equals(authenticatedAccount.getCustomer().getId())) {
            throw new SecurityValidationAccountException("There is a security failure validation");
        }
        return true;
    }


}
