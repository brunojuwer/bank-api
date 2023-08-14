package br.com.juwer.bankapi.domain.repository;

import br.com.juwer.bankapi.domain.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<List<Transaction>> findByAccountCode(String accountCode);
}
