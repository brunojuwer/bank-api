package br.com.juwer.bankapi.unit.service;

import br.com.juwer.bankapi.domain.model.Transaction;
import br.com.juwer.bankapi.domain.repository.TransactionRepository;
import br.com.juwer.bankapi.domain.service.TransactionService;
import br.com.juwer.bankapi.utils.FakeTransactionFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @InjectMocks
    TransactionService service;

    @Mock
    TransactionRepository repository;

    @Test
    void itShouldCreateAnNewTransacationWithSuccess() {
        Transaction transaction = FakeTransactionFactory.createNewTransaction();
        Transaction transactionWithId = FakeTransactionFactory.createNewTransactionWithId();

        when(repository.save(transaction)).thenReturn(transactionWithId);
        Transaction createdTransaction = service.save(transaction);

        verify(repository).save(transaction);
        assertEquals(transactionWithId, createdTransaction);
    }

    @Test
    void itShouldGetAnTransactionWithSuccess() {
        Transaction transactionWithId = FakeTransactionFactory.createNewTransactionWithId();

        when(repository.findById(transactionWithId.getId())).thenReturn(Optional.of(transactionWithId));
        Transaction transaction = service.findTransactionById(transactionWithId.getId());

        verify(repository).findById(transaction.getId());
        assertEquals(transactionWithId, transaction);
    }

    @Test
    void itShouldThrowAnExceptionWhenGetAnTransactionWithNonExistentId() {
        Transaction transactionWithId = FakeTransactionFactory.createNewTransactionWithId();

        when(repository.findById(transactionWithId.getId())).thenReturn(Optional.empty());
        Throwable exception = catchThrowable(() -> service.findTransactionById(transactionWithId.getId()));

        assertEquals("Transaction with id 1 not found", exception.getMessage());
    }
}
