package br.com.juwer.bankapi.domain.exceptions;

public class ExistingCustomerException extends ExistingEntityException {
    public ExistingCustomerException(String uniqueValue) {
        super(String.format("Customer with %s is already taken", uniqueValue));
    }
}
