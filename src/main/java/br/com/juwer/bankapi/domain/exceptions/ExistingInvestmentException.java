package br.com.juwer.bankapi.domain.exceptions;

public class ExistingInvestmentException extends ExistingEntityException {
    public ExistingInvestmentException(String name) {
        super(String.format("Investment with name %s is already taken", name));
    }
}
