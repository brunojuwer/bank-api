package br.com.juwer.bankapi.domain.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "loan_type")
@SequenceGenerator(name = "loan_type_seq", sequenceName = "loan_type_seq", allocationSize = 1)
public class LoanType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loan_type_seq")
    private Long id;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;
}
