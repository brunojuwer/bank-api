package br.com.juwer.bankapi.domain.service;

import br.com.juwer.bankapi.domain.exceptions.AccountNotFoundException;
import br.com.juwer.bankapi.domain.model.Account;
import br.com.juwer.bankapi.domain.model.Transaction;
import br.com.juwer.bankapi.domain.repository.AccountRepository;
import br.com.juwer.bankapi.domain.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountTransactionService {

    private final AccountService accountService;
    private final TransactionService transactionService;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;


    @Transactional
    public Transaction deposit(String accountCode, BigDecimal amount) {
        Account account = accountService.findByCode(accountCode);
        account.deposit(amount);

        Transaction transaction = new Transaction()
                .getNewInstance("CURRENT_ACCOUNT", accountCode, amount, Transaction.Operation.DEPOSIT);

        Transaction savedTransaction = transactionService.save(transaction);
        accountRepository.populateAccountTransactionTable(account.getCode(), transaction.getId());
        accountService.update(account);

        return savedTransaction;
    }

    @Transactional
    public Transaction withdraw(String accountCode, BigDecimal amount) {
        Account account = accountService.findByCode(accountCode);
        account.withdraw(amount);
        Transaction transaction = new Transaction()
                .getNewInstance("CURRENT_ACCOUNT", accountCode, amount, Transaction.Operation.WITHDRAW);

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
