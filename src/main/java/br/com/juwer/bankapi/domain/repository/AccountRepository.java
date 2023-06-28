package br.com.juwer.bankapi.domain.repository;

import br.com.juwer.bankapi.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {

    @Modifying
    @Query(value = "INSERT INTO account_transaction (account_code, transaction_id) " +
            "VALUES (:code, :transactionId)",
        nativeQuery = true)
    void populateAccountTransactionTable(String code, Long transactionId);

    Optional<Account> findByCode(String code);
}
