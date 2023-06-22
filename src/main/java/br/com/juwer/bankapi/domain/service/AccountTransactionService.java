package br.com.juwer.bankapi.domain.service;

import br.com.juwer.bankapi.domain.model.Account;
import br.com.juwer.bankapi.domain.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountTransactionService {

    private final AccountService accountService;
    private final TransactionService transactionService;


    @Transactional
    public Transaction deposit(Account account, Transaction transaction) {
       Transaction savedTransaction = transactionService.save(transaction);
       account.deposit(savedTransaction);
       accountService.save(account);

       return savedTransaction;
    }
}
