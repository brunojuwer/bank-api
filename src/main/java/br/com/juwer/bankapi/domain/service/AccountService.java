package br.com.juwer.bankapi.domain.service;

import br.com.juwer.bankapi.api.dto.input.AccountInputPassword;
import br.com.juwer.bankapi.domain.exceptions.AccountNotFoundException;
import br.com.juwer.bankapi.domain.model.Account;
import br.com.juwer.bankapi.domain.model.Customer;
import br.com.juwer.bankapi.domain.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository repository;
    private final CustomerService customerService;
    private final PasswordEncoder encoder;

    @Transactional
    public Account save(Account account) {
        customerService.save(account.getCustomer());
        account.setPassword(encoder.encode(account.getPassword()));
        return repository.save(account);
    }

    @Transactional
    public Account update(Account account) {
        return repository.save(account);
    }


    @Transactional
    public void updatePassword(Account account, AccountInputPassword accountInputPassword) {
//        final boolean currentPasswordMatches = encoder
//                .matches(accountInputPassword.currentPassword(), customer.getPassword());
//
//        if(!currentPasswordMatches) {
//            throw new CurrentPasswordDoesNotMatchException("Your current password does not match");
//        }
//        customer.setPassword(encoder.encode(accountInputPassword.newPassword()));
        repository.save(account);
    }

    public Account findByCode(String accountCode) {
        return repository.findByCode(accountCode)
                .orElseThrow(() -> new AccountNotFoundException(accountCode));
    }
}
