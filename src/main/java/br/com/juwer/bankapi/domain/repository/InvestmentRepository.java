package br.com.juwer.bankapi.domain.repository;

import br.com.juwer.bankapi.domain.model.Investment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {
    Optional<Investment> findByName(String name);
}
