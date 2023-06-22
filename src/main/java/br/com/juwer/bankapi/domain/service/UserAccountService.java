package br.com.juwer.bankapi.domain.service;

import br.com.juwer.bankapi.domain.model.Account;
import br.com.juwer.bankapi.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserAccountService {

    private final UserService userService;
    private final AccountService accountService;

    @Transactional
    public Account save(Long userId, Account account) {
        User user = userService.findUserById(userId);
        Account savedAccount = accountService.save(account);
        user.addNewAccount(account);
        return savedAccount;
    }

    public Account findAccountByOwnIdAndUserId(Long accountId, Long userId){
        return accountService.findAccountByOwnIdAndUserId(accountId, userId);
    }
}
