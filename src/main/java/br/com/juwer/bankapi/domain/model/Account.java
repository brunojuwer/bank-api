package br.com.juwer.bankapi.domain.model;

import br.com.juwer.bankapi.domain.exceptions.InvalidTransactionException;
import br.com.juwer.bankapi.domain.model.Transaction.Operation;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account implements UserDetails {

    @Id
    private String code;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Type type;

    private BigDecimal balance;

    @CreationTimestamp
    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "last_login_date")
    private OffsetDateTime lastLoginDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "account_transaction",
    joinColumns = @JoinColumn(name = "account_code"),
    inverseJoinColumns = @JoinColumn(name = "transaction_id"))
    private List<Transaction> transactions = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "account_investment",
        joinColumns = @JoinColumn(name = "account_code"),
        inverseJoinColumns = @JoinColumn(name = "investment_id"))
    private List<Investment> investments = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "account_loan",
        joinColumns = @JoinColumn(name = "account_code"),
        inverseJoinColumns = @JoinColumn(name = "loan_id"))
    private List<Loan> loans = new ArrayList<>();

    @PrePersist
    private void setCode() {
        this.code = generateAccountNumber();
    }

    private String generateAccountNumber() {
        String numbers = "0123456789";
        Random rndm_method = new Random();
        char[] codes = new char[8];

        for (int i = 0; i < 8; i++) {
            codes[i] = numbers
                .charAt(rndm_method.nextInt(numbers.length()));
        }
        return new String(codes);
    }


    public void depositOrWithDraw(BigDecimal amount, Operation operation) {

        boolean isValueGreaterThanZero = amount.compareTo(BigDecimal.ZERO) > 0;
        boolean isValueLessThanZero = amount.compareTo(BigDecimal.ZERO) < 0;
        boolean isADeposit = operation.equals(Operation.DEPOSIT);
        boolean isAWithDraw = operation.equals(Operation.WITHDRAW);

        if(isValueGreaterThanZero && isADeposit) {
            this.deposit(amount);
        }
        else if(isValueLessThanZero && isAWithDraw) {
            amount = amount.multiply(BigDecimal.valueOf(-1));
            this.withdraw(amount);
        } else {
            throw new InvalidTransactionException("Invalid value for a " + operation + " operation");
        }

    }

    private void withdraw(BigDecimal ammount) {
        this.balance = this.balance.subtract(ammount);
    }

    private void deposit(BigDecimal ammount) {
        this.balance = this.balance.add(ammount);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("CUSTOMER_ACCOUNT"));
    }

    @Override
    public String getUsername() {
        return this.code;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Getter
    public enum Type {
        PF,
        PJ
    }
}
