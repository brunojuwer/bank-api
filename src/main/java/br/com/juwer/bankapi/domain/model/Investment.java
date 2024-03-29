package br.com.juwer.bankapi.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "investment")
@SequenceGenerator(name = "investment_seq", sequenceName = "investment_seq", allocationSize = 1)
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "investment_seq")
    private Long id;

    private String name;

    private BigDecimal balance;

    private BigDecimal profitability;

    private String description;

    @CreationTimestamp
    @Column(nullable = false, name = "created_at")
    private OffsetDateTime createdAt;

    public void subtractBalance(BigDecimal amount) {
//        amount = convertNegativeNumber(amount);
        this.balance = this.balance.subtract(amount);
    }

    public void addToBalance(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    public BigDecimal convertNegativeNumber(BigDecimal number) {
        return number.multiply(BigDecimal.valueOf(-1));
    }
}