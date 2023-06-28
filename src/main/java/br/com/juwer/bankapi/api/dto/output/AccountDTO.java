package br.com.juwer.bankapi.api.dto.output;

import br.com.juwer.bankapi.domain.model.Account.Type;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class AccountDTO {

    private String code;
    private Type type;
    private BigDecimal balance;
    private OffsetDateTime createdAt;

    private CustomerDTO customer;
}
