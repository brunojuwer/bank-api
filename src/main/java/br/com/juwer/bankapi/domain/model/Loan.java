package br.com.juwer.bankapi.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "loan")
@SequenceGenerator(name = "loan_seq", sequenceName = "loan_seq", allocationSize = 1)
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "loan_seq")
    private Long id;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "remaining_amount")
    private BigDecimal remainingAmount;

    @Column(name = "amount_paid")
    private BigDecimal amountPaid;

    private BigDecimal interest;

    private String description;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    @CreationTimestamp
    @Column(name = "issued_at", nullable = false)
    private OffsetDateTime issuedAt;

    @CreationTimestamp
    @Column(name = "finished_at", nullable = false)
    private OffsetDateTime finishedAt;

    @ManyToOne
    @JoinColumn(name = "loan_type_id")
    private LoanType loanType;

    public enum Status {
        PENDING,
        DENIED,
        IN_PAYMENT,
        FINISHED
    }

}
