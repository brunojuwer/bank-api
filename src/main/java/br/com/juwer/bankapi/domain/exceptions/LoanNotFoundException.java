package br.com.juwer.bankapi.domain.exceptions;

public class LoanNotFoundException extends EntityNotFoundException {
    public LoanNotFoundException(Long loanId) {
        super(String.format("Loan with id %d not found", loanId));
    }

    public LoanNotFoundException(Long loanId, String accountCode) {
        super(String.format("Account %s with Loan id %d not found", accountCode, loanId));
    }
}
