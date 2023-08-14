package br.com.juwer.bankapi.domain.service;

import br.com.juwer.bankapi.domain.exceptions.AccountNotFoundException;
import br.com.juwer.bankapi.domain.model.Account;
import br.com.juwer.bankapi.domain.model.Transaction;
import br.com.juwer.bankapi.domain.repository.AccountRepository;
import br.com.juwer.bankapi.domain.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountTransactionService {

    private final AccountService accountService;
    private final TransactionService transactionService;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;


    @Transactional
    public Transaction depositOrWithdraw(String accountCode, Transaction transaction) {
        Account account = accountService.findByCode(accountCode);
        account.depositOrWithDraw(transaction.getAmount(), transaction.getOperation());

        transaction.setProduct("CURRENT_ACCOUNT");
        transaction.setAccountCode(accountCode);
        Transaction savedTransaction = transactionService.save(transaction);
        accountRepository.populateAccountTransactionTable(account.getCode(), transaction.getId());
        accountService.update(account);

        return savedTransaction;
    }

    public void create(Transaction transaction) {
        transactionService.save(transaction);
    }

    public List<Transaction> findAll(String accountCode) {
        return transactionRepository.findByAccountCode(accountCode)
                .orElseThrow(() -> new AccountNotFoundException(accountCode));
    }
}
