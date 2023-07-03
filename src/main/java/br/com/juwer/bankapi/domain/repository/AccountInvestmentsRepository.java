package br.com.juwer.bankapi.domain.repository;

import br.com.juwer.bankapi.domain.model.AccountInvestmentRelationshipIDKeys;
import br.com.juwer.bankapi.domain.model.AccountInvestments;
import br.com.juwer.bankapi.domain.projections.AccountInvestmentResume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccountInvestmentsRepository extends JpaRepository<AccountInvestments, AccountInvestmentRelationshipIDKeys> {

    @Query("FROM AccountInvestments ai " +
            "JOIN FETCH ai.account a " +
            "JOIN FETCH ai.investment i " +
            "WHERE a.code = :accountCode AND i.id = :investmentId")
    Optional<AccountInvestments> findByIds(String accountCode, Long investmentId);

    @Query("FROM AccountInvestments ai " +
            "JOIN FETCH ai.account a " +
            "JOIN FETCH ai.investment i " +
            "WHERE a.code = :accountCode")
    Optional<List<AccountInvestments>> findAll(String accountCode);
}
