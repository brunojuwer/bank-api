package br.com.juwer.bankapi.domain.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "loan_type")
@SequenceGenerator(name = "loan_type_seq", sequenceName = "loan_type_seq", allocationSize = 1)
@NoArgsConstructor
public class LoanType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loan_type_seq")
    private Long id;

    private String name;

    private String description;
}
