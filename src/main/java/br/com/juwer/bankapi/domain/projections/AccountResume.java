package br.com.juwer.bankapi.domain.projections;


import br.com.juwer.bankapi.domain.model.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountResume {

    private String code;
    private Account.Type type;
    private BigDecimal balance;
    private OffsetDateTime createdAt;
    private OffsetDateTime lastLoginDate;
}
