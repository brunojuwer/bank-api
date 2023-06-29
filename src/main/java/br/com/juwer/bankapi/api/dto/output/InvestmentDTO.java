package br.com.juwer.bankapi.api.dto.output;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class InvestmentDTO {

    private Long id;

    private String name;

    private BigDecimal balance;

    private BigDecimal profitability;

    private String description;
}
