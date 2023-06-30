package br.com.juwer.bankapi.domain.repository;

import br.com.juwer.bankapi.domain.model.Account;
import br.com.juwer.bankapi.domain.projections.AccountResume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {

    Optional<Account> findByCode(String code);

    @Query("select new br.com.juwer.bankapi.domain.projections.AccountResume(" +
            "a.code, a.type, a.balance, a.createdAt, a.lastLoginDate" + ") from Account a " +
            "where a.code = :accountCode")
    Optional<AccountResume> findResumeByCode(String accountCode);

    @Query("from Account a join fetch a.customer where a.code = :code")
    Optional<Account> findByCodeWithCustomer(String code);
    @Modifying
    @Query(value = "INSERT INTO account_transaction (account_code, transaction_id) " +
            "VALUES (:code, :transactionId)",
        nativeQuery = true)

    void populateAccountTransactionTable(String code, Long transactionId);
}
