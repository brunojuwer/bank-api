package br.com.juwer.bankapi.unit.service;

import br.com.juwer.bankapi.domain.model.Account;
import br.com.juwer.bankapi.domain.model.Transaction;
import br.com.juwer.bankapi.domain.service.AccountService;
import br.com.juwer.bankapi.domain.service.AccountTransactionService;
import br.com.juwer.bankapi.domain.service.TransactionService;
import br.com.juwer.bankapi.utils.FakeAccountFactory;
import br.com.juwer.bankapi.utils.FakeTransactionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountTransactionTest {

    @InjectMocks
    AccountTransactionService service;

    @Mock
    AccountService accountService;

    @Mock
    TransactionService transactionService;

    @Test
    void itShouldCreateAccountTransactionWithSuccess() {
        Transaction newTransaction = FakeTransactionFactory.createNewTransaction();
        Transaction transactionWithId = FakeTransactionFactory.createNewTransactionWithId();
        Account account = FakeAccountFactory.createAccountWithId();
        Account accountWithNewBalance = FakeAccountFactory.createAccountWithIdAndNewBalance();

        when(accountService.save(account)).thenReturn(accountWithNewBalance);
        when(transactionService.save(newTransaction)).thenReturn(transactionWithId);

        Transaction savedTransaction = service
                .depositOrWithdraw(account.getCode(), newTransaction);

        verify(accountService).save(account);
        verify(transactionService).save(newTransaction);

//        assertEquals(accountWithNewBalance, savedTransaction);
    }
}
