package br.com.juwer.bankapi.unit.service;

import br.com.juwer.bankapi.domain.exceptions.AccountNotFoundException;
import br.com.juwer.bankapi.domain.exceptions.UserNotFoundException;
import br.com.juwer.bankapi.domain.model.Account;
import br.com.juwer.bankapi.domain.model.Customer;
import br.com.juwer.bankapi.domain.model.Transaction;
import br.com.juwer.bankapi.domain.service.AccountService;
import br.com.juwer.bankapi.domain.service.UserAccountService;
import br.com.juwer.bankapi.domain.service.UserService;
import br.com.juwer.bankapi.utils.FakeAccountFactory;
import br.com.juwer.bankapi.utils.FakeTransactionFactory;
import br.com.juwer.bankapi.utils.FakeUserFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerAccountServiceTest {

    @InjectMocks
    UserAccountService service;

    @Mock
    AccountService accountService;

    @Mock
    UserService userService;

    @Test
    void itShouldSaveAnAccountUserWithSuccess() {
        Account account = FakeAccountFactory.createAccount();
        Account accountWithId = FakeAccountFactory.createAccountWithId();
        Customer customer = FakeUserFactory.generateSimpleUserWithId();

        when(userService.findUserById(customer.getId())).thenReturn(customer);
        when(accountService.save(account)).thenReturn(accountWithId);

        Account createdAccount = service.save(customer.getId(), account);

        verify(userService).findUserById(customer.getId());
        verify(accountService).save(account);

        assertEquals(createdAccount, accountWithId);
    }

    @Test
    void itShouldThrowAnExceptionWhenSaveAnAccountWithNonExistentUserId() {
        Account account = FakeAccountFactory.createAccount();
        Customer customer = FakeUserFactory.generateSimpleUserWithId();

        when(userService.findUserById(any())).thenThrow(new UserNotFoundException("User with id 1 not found"));

        Throwable exception = catchThrowable(() -> service.save(customer.getId(), account));

        verify(accountService, never()).save(account);

        assertEquals("User with id 1 not found", exception.getMessage());
    }

    @Test
    void itShouldGetAnUserAccountWithSuccess() {
//        Customer customer = FakeUserFactory.generateSimpleUserWithId();
//        Account accountWithId = FakeAccountFactory.createAccountWithId();
//
//        when(accountService.findByCode(accountWithId.getCode())).thenReturn(accountWithId);
////        Account foundAccount = service.findAccountByOwnIdAndUserId(accountWithId.getAc );
//
//        verify(accountService).findAccountByOwnIdAndUserId(accountWithId.getId(), customer.getId());
//        assertEquals(accountWithId, foundAccount);
    }

    @Test
    void itShouldThrowAnExceptionWhenGetAnAccountWithNonExistentAccountIdAndUserId() {
        Transaction transactionWithId = FakeTransactionFactory.createNewTransactionWithId();

        when(accountService.findByCode(transactionWithId.getAccountCode()))
                .thenThrow(new AccountNotFoundException(transactionWithId.getAccountCode()));

        Throwable exception = catchThrowable(() ->
                service.findAccountByOwnIdAndUserId(transactionWithId.getAccountCode()));

        assertEquals("Account with id 1 not found", exception.getMessage());
    }

}