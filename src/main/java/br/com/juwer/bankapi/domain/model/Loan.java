package br.com.juwer.bankapi.domain.model;

import br.com.juwer.bankapi.api.dto.input.LoanPaymentInput;
import br.com.juwer.bankapi.domain.exceptions.InvalidTransactionException;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.OffsetDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "loan")
@SequenceGenerator(name = "loan_seq", sequenceName = "loan_seq", allocationSize = 1)
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "loan_seq")
    private Long id;

    @Column(name = "required_amount")
    private BigDecimal requiredAmount;

    @Column(name = "remaining_amount")
    private BigDecimal remainingAmount;

    @Column(name = "amount_paid")
    private BigDecimal amountPaid;

    private BigDecimal interest;

    private Integer installments;

    @Column(name = "installments_value")
    private BigDecimal installmentsValue;

    @Column(name = "installments_due_day")
    private Integer installmentsDueDay;

    private String description;

    @Enumerated(EnumType.STRING)
    @Setter(AccessLevel.NONE)
    private Status status = Status.PENDING;

    @CreationTimestamp
    @Column(name = "issued_at", nullable = false)
    private OffsetDateTime issuedAt;

    @Column(name = "finished_at")
    private OffsetDateTime finishedAt;

    @ManyToOne
    @JoinColumn(name = "loan_type_id")
    private LoanType loanType;


    public void setInitialLoanState(LoanType type){
        this.setLoanType(type);
        this.setRemainingAmount(BigDecimal.ZERO);
        this.setAmountPaid(BigDecimal.ZERO);
        this.setInterest(BigDecimal.ZERO);
    }

    @Getter
    public enum Status {
        PENDING,
        DENIED,
        IN_PAYMENT,
        FINISHED;
    }

    public void setLoanPaymentDetails(LoanPaymentInput input) {
        if(!this.status.equals(Status.IN_PAYMENT)) {
            throw new InvalidTransactionException("To set loan details the status need to be IN_PAYMENT");
        }
        this.interest = input.getInterest();
        this.installments = input.getLoanTenure();
        this.installmentsDueDay = input.getInstallmentsDueDay();
        this.remainingAmount = calcLoanValue(this.requiredAmount, input.getInterest(), input.getLoanTenure());
        this.installmentsValue = calcInstallmentsUnitValue(this.remainingAmount, this.installments);
    }

    private BigDecimal calcLoanValue(BigDecimal principal, BigDecimal interest, int period) {
        final BigDecimal HUNDRED = new BigDecimal("100.00");
        BigDecimal convertedInterest = interest.divide(HUNDRED);
        BigDecimal one = new BigDecimal("1.00");

        BigDecimal interestPlusOne = convertedInterest.add(one);
        BigDecimal amount = principal.multiply(interestPlusOne.pow(period));

        return amount.setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal calcInstallmentsUnitValue(BigDecimal remainingAmount, int installments) {
        return remainingAmount.divide(BigDecimal.valueOf(installments), RoundingMode.HALF_UP);
    }

    public void deny() {
        if(this.status.equals(Status.FINISHED) || this.status.equals(Status.IN_PAYMENT)) {
            throw new InvalidTransactionException("Only accept if STATUS are equals to PENDING.");
        }
        this.status = Status.DENIED;
    }

    public void approve() {
        if(this.status.equals(Status.FINISHED) || this.status.equals(Status.DENIED)) {
            throw new InvalidTransactionException("Only accept if STATUS are equals to PENDING.");
        }
        this.status = Status.IN_PAYMENT;
    }

    public void finished() {
        if(this.status.equals(Status.PENDING) || this.status.equals(Status.DENIED)) {
            throw new InvalidTransactionException("Only accept if STATUS are equals to IN_PAYMENT.");
        }
        if(this.remainingAmount.equals(BigDecimal.ZERO)) {
            throw new InvalidTransactionException("Remaining amount need to be equals to ZERO");
        }
        this.status = Status.FINISHED;
    }

    public void payInstallmet() {
        this.remainingAmount = this.remainingAmount.subtract(this.installmentsValue);
        this.amountPaid = this.amountPaid.add(this.installmentsValue);

        if(this.remainingAmount.equals(BigDecimal.ZERO)) {
            this.finished();
            this.setFinishedAt(OffsetDateTime.now());
        }
    }

    public boolean isInstallmentPayday() {
        return this.installmentsDueDay.equals(OffsetDateTime.now().getDayOfMonth());
    }
}