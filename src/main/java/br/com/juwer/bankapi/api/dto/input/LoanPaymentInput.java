package br.com.juwer.bankapi.api.dto.input;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class LoanPaymentInput {

    private BigDecimal interest;
    private int loanTenure;
    private int installmentsDueDay;
}
