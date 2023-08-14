package br.com.juwer.bankapi.domain.repository;

import br.com.juwer.bankapi.domain.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    @Query("from Loan l join fetch Account a " +
           "join fetch l.loanType " +
           "where a.code = :accountCode")
    Optional<List<Loan>> findAllByAccountCode(String accountCode);

    @Query("from Loan l join fetch Account a " +
            "join fetch l.loanType " +
            "where a.code = :accountCode and l.id = :loanId")
    Optional<Loan> findByAccountCodeAndLoanId(String accountCode, Long loanId);
}