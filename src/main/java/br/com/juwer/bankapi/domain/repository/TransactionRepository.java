package br.com.juwer.bankapi.domain.repository;

import br.com.juwer.bankapi.domain.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
