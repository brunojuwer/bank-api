package br.com.juwer.bankapi.domain.service;

import br.com.juwer.bankapi.domain.model.Account;
import br.com.juwer.bankapi.domain.model.Transaction;
import br.com.juwer.bankapi.domain.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountTransactionService {

    private final AccountService accountService;
    private final TransactionService transactionService;
    private final AccountRepository accountRepository;


    @Transactional
    public Transaction depositOrWithdraw(String accountCode, Transaction transaction) {
        Account account = accountService.findByCode(accountCode);
        account.depositOrWithDraw(transaction.getAmount(), transaction.getOperation());

        transaction.setAccountCode(accountCode);
        Transaction savedTransaction = transactionService.save(transaction);
        accountRepository.populateAccountTransactionTable(account.getCode(), transaction.getId());
        accountService.update(account);

        return savedTransaction;
    }
}
