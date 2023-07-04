package br.com.juwer.bankapi.api.dto.output;

import br.com.juwer.bankapi.domain.model.Loan;
import br.com.juwer.bankapi.domain.model.LoanType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class LoanDTO {

    private Long id;

    private BigDecimal totalAmount;

    private BigDecimal remainingAmount;

    private BigDecimal amountPaid;

    private BigDecimal interest;

    private String description;

    private LoanType loanType;

    private Loan.Status status;

    private OffsetDateTime issuedAt;

    private OffsetDateTime finishedAt;
}
