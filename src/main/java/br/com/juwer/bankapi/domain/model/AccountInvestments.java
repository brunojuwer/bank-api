package br.com.juwer.bankapi.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Random;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "account_investments")
@IdClass(AccountInvestmentRelationshipIDKeys.class)
public class AccountInvestments {

    private String code;

    @OneToOne
    @MapsId
    @Id
    private Account account;

    @OneToOne
    @MapsId
    @Id
    private Investment investment;

    @Column(name = "total_balance", nullable = false)
    private BigDecimal totalBalance;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;


    @PrePersist
    private void setCode() {
        this.code = UUID.randomUUID().toString();
    }

    public void reclaim(BigDecimal amount) {

        this.totalBalance = this.totalBalance.subtract(amount);
    }

    public void application(BigDecimal amount) {
        this.totalBalance = this.totalBalance.add(amount);
    }
}
