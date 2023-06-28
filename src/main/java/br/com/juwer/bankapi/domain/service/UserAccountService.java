package br.com.juwer.bankapi.domain.service;

import br.com.juwer.bankapi.domain.model.Account;
import br.com.juwer.bankapi.domain.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserAccountService {

    private final CustomerService customerService;
    private final AccountService accountService;

    @Transactional
    public Account save(Long userId, Account account) {
        Customer customer = customerService.findCustomerById(userId);
        Account savedAccount = accountService.save(account);
//        customer.addNewAccount(account);
        return savedAccount;
    }

    public Account findAccountByOwnIdAndUserId(String accountCode){
        return accountService.findByCode(accountCode);
    }
}
