package br.com.juwer.bankapi.api.dto.output;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
public class AccountInvestmentsDTO {

    private String code;
    private String investmentName;
    private BigDecimal totalBalance;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
