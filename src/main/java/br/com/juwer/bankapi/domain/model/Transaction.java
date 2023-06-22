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
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_sequence")
    private Long id;
    private BigDecimal ammount;

    @Enumerated(EnumType.STRING)
    private Operation operation;

    @Column(name = "created_at")
    @CreationTimestamp
    private OffsetDateTime createdAt;

    @Getter
    public enum Operation {
        DEPOSIT,
        WITHDRAW,
        TRANSFER,
        PIX
    }
}
