package br.com.juwer.bankapi.config.security.verification;

import br.com.juwer.bankapi.domain.exceptions.SecurityValidationAccountException;
import br.com.juwer.bankapi.domain.model.Account;
import br.com.juwer.bankapi.domain.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityUtils {

    private final AccountService accountService;

    public boolean verifyIfAccountMatches(String accountCode) {
        Account authenticatedAccount = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(!accountCode.equals(authenticatedAccount.getCode())) {
            throw new SecurityValidationAccountException("There is a security failure validation");
        }
        return true;
    }


}
