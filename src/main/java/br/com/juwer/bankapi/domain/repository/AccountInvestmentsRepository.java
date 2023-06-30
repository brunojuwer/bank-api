package br.com.juwer.bankapi.domain.repository;

import br.com.juwer.bankapi.domain.model.AccountInvestmentRelationshipIDKeys;
import br.com.juwer.bankapi.domain.model.AccountInvestments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountInvestmentsRepository extends JpaRepository<AccountInvestments, AccountInvestmentRelationshipIDKeys> {

//    @Query("from AccountInvestments ai join fetch ai.account a where a.code = :accountCode" +
//            " and join fetch ai.investment i where i.id = :investmentId")
//    Optional<AccountInvestments> findByCodes(String accountCode, Long investmentId);
}
