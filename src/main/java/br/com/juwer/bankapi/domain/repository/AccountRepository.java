package br.com.juwer.bankapi.domain.repository;

import br.com.juwer.bankapi.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query(value = "SELECT a.id, a.type , a.balance, a.created_at FROM _user_account ua " +
            "JOIN account a ON ua.account_id = a.id " +
            "JOIN _user u ON u.id = ua.user_id " +
            "WHERE ua.user_id = :userId AND ua.account_id = :accountId", nativeQuery = true)
    Optional<Account> findAccountByOwnIdAndUserId(Long accountId, Long userId);
}
