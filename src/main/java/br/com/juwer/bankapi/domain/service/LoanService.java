package br.com.juwer.bankapi.domain.service;

import br.com.juwer.bankapi.api.dto.input.LoanPaymentInput;
import br.com.juwer.bankapi.domain.exceptions.AccountNotFoundException;
import br.com.juwer.bankapi.domain.exceptions.LoanNotFoundException;
import br.com.juwer.bankapi.domain.model.Loan;
import br.com.juwer.bankapi.domain.model.LoanType;
import br.com.juwer.bankapi.domain.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
    private final AccountService accountService ;
    private final LoanTypeService loanTypeService;

    @Transactional
    public Loan create(Loan loan) {
        LoanType loanType = loanTypeService.findLoanTypeById(loan.getLoanType().getId());
        loan.setInitialLoanState(loanType);
        return loanRepository.save(loan);
    }

    public Loan update(Loan loan) {
        return loanRepository.save(loan);
    }

    public Loan findById(Long loanId) {
        return loanRepository.findById(loanId)
                .orElseThrow(
                    ()-> new LoanNotFoundException(loanId)
                );
    }

    public List<Loan> findAllByAccountCode(String accountCode) {
        return loanRepository.findAllByAccountCode(accountCode)
                .orElseThrow(
                    ()-> new AccountNotFoundException(accountCode)
                );
    }

    public Loan findByAccountCodeAndLoanId(String accountCode, Long loanId) {

        accountService.findByCode(accountCode);
        return loanRepository.findByAccountCodeAndLoanId(accountCode, loanId)
                .orElseThrow(
                    ()-> new LoanNotFoundException(loanId, accountCode)
                );
    }

    public void denyLoan(Long loanId) {
        Loan loan = findById(loanId);
        loan.deny();
        loan.setFinishedAt(OffsetDateTime.now());
        this.update(loan);
    }

    @Transactional
    public void approveLoan(Long loanId, LoanPaymentInput loanPaymentInput) {
        Loan loan = findById(loanId);
        loan.approve();
        loan.setLoanPaymentDetails(loanPaymentInput);
        this.update(loan);
    }
}
