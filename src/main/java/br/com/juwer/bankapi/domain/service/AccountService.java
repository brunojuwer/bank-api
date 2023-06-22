package br.com.juwer.bankapi.domain.service;

import br.com.juwer.bankapi.domain.exceptions.AccountNotFoundException;
import br.com.juwer.bankapi.domain.model.Account;
import br.com.juwer.bankapi.domain.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository repository;

    public Account save(Account account) {
        return repository.save(account);
    }


    public Account findAccountByOwnIdAndUserId(Long accountId, Long userId) {
        return repository.findAccountByOwnIdAndUserId(accountId, userId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));
    }
}
