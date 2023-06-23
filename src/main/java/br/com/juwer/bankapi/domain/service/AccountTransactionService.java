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
    public Transaction deposit(Account account, Transaction transaction) {
       account.depositOrWithDraw(transaction.getAmmount(), transaction.getOperation());
       Transaction savedTransaction = transactionService.save(transaction);
       accountRepository.polulateAccountTransactionTable(account.getId(), transaction.getId());
       accountService.save(account);

       return savedTransaction;
    }
}
