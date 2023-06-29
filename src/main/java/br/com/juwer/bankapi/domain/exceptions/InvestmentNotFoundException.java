package br.com.juwer.bankapi.domain.exceptions;

public class InvestmentNotFoundException extends EntityNotFoundException {

    public InvestmentNotFoundException(String message) {
        super(message);
    }

    public InvestmentNotFoundException(Long investmentId) {
        this(String.format("Investment with id %d not found", investmentId));
    }
}
