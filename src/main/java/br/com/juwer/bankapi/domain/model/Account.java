package br.com.juwer.bankapi.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_sequence")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Type type;
    private BigDecimal balance;

    @CreationTimestamp
    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @ManyToMany
    @JoinTable(name = "account_transaction",
    joinColumns = @JoinColumn(name = "account_id"),
    inverseJoinColumns = @JoinColumn(name = "transaction_id"))
    private List<Transaction> transactions = new ArrayList<>();


    public void deposit(Transaction transaction) {
        this.balance = this.balance.add(transaction.getAmmount());
        this.setTransactions(List.of(transaction));
    }

    @Getter
    public enum Type {
        CURRENT,
        SAVINGS
    }
}
