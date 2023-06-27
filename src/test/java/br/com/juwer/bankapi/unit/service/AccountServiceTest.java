package br.com.juwer.bankapi.unit.service;

import br.com.juwer.bankapi.domain.model.Account;
import br.com.juwer.bankapi.domain.repository.AccountRepository;
import br.com.juwer.bankapi.domain.service.AccountService;
import br.com.juwer.bankapi.utils.FakeAccountFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @InjectMocks
    AccountService service;

    @Mock
    AccountRepository repository;

    @Test
    void itShouldCreateAnAccountWithSuccess() {
        Account account = FakeAccountFactory.createAccount();
        Account accountWithId = FakeAccountFactory.createAccountWithId();

        when(repository.save(account)).thenReturn(accountWithId);
        Account createdAccount = service.save(account);

        verify(repository).save(account);
        assertEquals(createdAccount, accountWithId);
    }

    @Test
    void itShouldGetAnAccountWithSuccess() {
        Account accountWithId = FakeAccountFactory.createAccountWithId();

        when(repository.findByCode(accountWithId.getCode()))
                .thenReturn(Optional.of(accountWithId));

        Account createdAccount = service.findByCode(accountWithId.getCode());

        verify(repository).findByCode(accountWithId.getCode());
        assertEquals(accountWithId, createdAccount);
    }

    @Test
    void itShouldThrowAnExceptionWhenGetAnAccountWithNonExistentId() {
        Account accountWithId = FakeAccountFactory.createAccountWithId();

        when(repository.findByCode(accountWithId.getCode()))
                .thenReturn(Optional.empty());
        Throwable exception = catchThrowable(() ->
                service.findByCode(accountWithId.getCode()));

        assertEquals("Account with id 1 not found", exception.getMessage());
    }
}
