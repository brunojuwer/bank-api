package br.com.juwer.bankapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountInvestmentRelationshipIDKeys {
    private Account account;
    private Investment investment;
}
