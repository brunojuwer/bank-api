package br.com.juwer.bankapi.domain.service;

import br.com.juwer.bankapi.domain.exceptions.ExistingInvestmentException;
import br.com.juwer.bankapi.domain.exceptions.InvestmentNotFoundException;
import br.com.juwer.bankapi.domain.model.Investment;
import br.com.juwer.bankapi.domain.repository.InvestmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class InvestmentService {

    private final InvestmentRepository repository;


    public Investment create(Investment investment) {
        this.verifyIfInvesmentAlreadyExistsByName(investment.getName());
        investment.setBalance(BigDecimal.ZERO);
        return repository.save(investment);
    }


    public Investment update(Investment investment) {
        return repository.save(investment);
    }

    @Transactional
    public Investment updateProfitabillity(Long investmentId, BigDecimal profitabillity) {
        Investment investment = findInvestmentById(investmentId);
        investment.setProfitability(profitabillity);

        return repository.save(investment);
    }

    public void delete(Long investmentId) {
        this.findInvestmentById(investmentId);
        repository.deleteById(investmentId);
    }

    public Investment findInvestmentById(Long investmentId) {
        return repository.findById(investmentId)
                .orElseThrow(() -> new InvestmentNotFoundException(investmentId));
    }

    public void verifyIfInvesmentAlreadyExistsByName(String name) {
        repository.findByName(name).ifPresent( investment -> {
                throw new ExistingInvestmentException(investment.getName());
            }
        );
    }
}
