package br.com.juwer.bankapi.unit.service;

import br.com.juwer.bankapi.domain.exceptions.AccountNotFoundException;
import br.com.juwer.bankapi.domain.exceptions.UserNotFoundException;
import br.com.juwer.bankapi.domain.model.Account;
import br.com.juwer.bankapi.domain.model.Transaction;
import br.com.juwer.bankapi.domain.model.User;
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
public class UserAccountServiceTest {

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
        User user = FakeUserFactory.generateSimpleUserWithId();

        when(userService.findUserById(user.getId())).thenReturn(user);
        when(accountService.save(account)).thenReturn(accountWithId);

        Account createdAccount = service.save(user.getId(), account);

        verify(userService).findUserById(user.getId());
        verify(accountService).save(account);

        assertEquals(createdAccount, accountWithId);
    }

    @Test
    void itShouldThrowAnExceptionWhenSaveAnAccountWithNonExistentUserId() {
        Account account = FakeAccountFactory.createAccount();
        User user = FakeUserFactory.generateSimpleUserWithId();

        when(userService.findUserById(any())).thenThrow(new UserNotFoundException("User with id 1 not found"));

        Throwable exception = catchThrowable(() -> service.save(user.getId(), account));

        verify(accountService, never()).save(account);

        assertEquals("User with id 1 not found", exception.getMessage());
    }

    @Test
    void itShouldGetAnUserAccountWithSuccess() {
        User user = FakeUserFactory.generateSimpleUserWithId();
        Account accountWithId = FakeAccountFactory.createAccountWithId();

        when(accountService.findAccountByOwnIdAndUserId(accountWithId.getId(), user.getId())).thenReturn(accountWithId);
        Account foundAccount = service.findAccountByOwnIdAndUserId(accountWithId.getId(), user.getId());

        verify(accountService).findAccountByOwnIdAndUserId(accountWithId.getId(), user.getId());
        assertEquals(accountWithId, foundAccount);
    }

    @Test
    void itShouldThrowAnExceptionWhenGetAnAccountWithNonExistentAccountIdAndUserId() {
        Transaction transactionWithId = FakeTransactionFactory.createNewTransactionWithId();

        when(accountService.findAccountByOwnIdAndUserId(transactionWithId.getId(), 1L))
                .thenThrow(new AccountNotFoundException(1L));

        Throwable exception = catchThrowable(() ->
                service.findAccountByOwnIdAndUserId(transactionWithId.getId(), 1L));

        assertEquals("Account with id 1 not found", exception.getMessage());
    }

}