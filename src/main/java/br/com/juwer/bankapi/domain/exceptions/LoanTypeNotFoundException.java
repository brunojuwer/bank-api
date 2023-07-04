package br.com.juwer.bankapi.domain.exceptions;

public class LoanTypeNotFoundException extends EntityNotFoundException {

    public LoanTypeNotFoundException(String message) {
        super(message);
    }

    public LoanTypeNotFoundException(Long loanId) {
        this(String.format("Loan Type with id %d not found", loanId));
    }
}
