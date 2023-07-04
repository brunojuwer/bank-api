package br.com.juwer.bankapi.domain.repository;

import br.com.juwer.bankapi.domain.model.LoanType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanTypeRepository extends JpaRepository<LoanType, Long> {
}
