package br.com.juwer.bankapi.api.dto.output;

import br.com.juwer.bankapi.domain.model.Account.Type;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class AccountDTOResume {

    private String accountCode;
    private Type type;
    private BigDecimal balance;
    private OffsetDateTime createdAt;
    private OffsetDateTime lastLoginDate;
}
