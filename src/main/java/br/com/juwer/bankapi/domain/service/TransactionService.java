package br.com.juwer.bankapi.domain.service;

import br.com.juwer.bankapi.domain.exceptions.TransactionNotFoundException;
import br.com.juwer.bankapi.domain.model.Transaction;
import br.com.juwer.bankapi.domain.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository repository;

    @Transactional
    public Transaction save(Transaction transaction) {
        return repository.save(transaction);
    }

    public Transaction findTransactionById(Long transactionId) {
        return repository.findById(transactionId)
                .orElseThrow(() -> new TransactionNotFoundException(transactionId));
    }
}
