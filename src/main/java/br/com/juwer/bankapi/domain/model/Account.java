package br.com.juwer.bankapi.domain.model;

import br.com.juwer.bankapi.domain.exceptions.InvalidTransactionException;
import br.com.juwer.bankapi.domain.model.Transaction.Operation;
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


    public void depositOrWithDraw(BigDecimal ammount, Operation operation) {

        boolean isValueGreaterThanZero = ammount.compareTo(BigDecimal.ZERO) > 0;
        boolean isValueLessThanZero = ammount.compareTo(BigDecimal.ZERO) < 0;
        boolean isADeposit = operation.equals(Operation.DEPOSIT);
        boolean isAWithDraw = operation.equals(Operation.WITHDRAW);

        if(isValueGreaterThanZero && isADeposit) {
            this.deposit(ammount);
        }
        else if(isValueLessThanZero && isAWithDraw) {
            this.withDraw(ammount);
        } else {
            throw new InvalidTransactionException("Invalid value for a " + operation + " operation");
        }

    }

    private void withDraw(BigDecimal ammount) {
        this.balance = this.balance.subtract(ammount);
    }

    private void deposit(BigDecimal ammount) {
        this.balance = this.balance.add(ammount);
    }

    @Getter
    public enum Type {
        CURRENT,
        SAVINGS
    }
}
