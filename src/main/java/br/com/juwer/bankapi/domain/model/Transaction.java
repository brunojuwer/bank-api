package br.com.juwer.bankapi.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaction")
@SequenceGenerator(name = "transaction_seq", sequenceName = "transaction_seq", allocationSize = 1)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")
    private Long id;

    @Column(name = "account_code")
    private String accountCode;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private Operation operation;

    @Column(nullable = false)
    private String product;

    @CreationTimestamp
    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    public Transaction getNewInstance(String product, String accountCode, BigDecimal amount, Operation operation) {
        this.setProduct(product);
        this.setAmount(amount);
        this.setAccountCode(accountCode);
        this.setOperation(operation);
        return this;
    }

    @Getter
    public enum Operation {
        DEPOSIT,
        WITHDRAW,
        TRANSFER,
        APPLICATION,
        INSTALLMENT_PAYMENT,
        RECLAIM,
        PIX
    }
}
