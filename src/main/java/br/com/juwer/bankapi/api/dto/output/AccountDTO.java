package br.com.juwer.bankapi.api.dto.output;

import br.com.juwer.bankapi.domain.model.Account.Type;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record AccountDTO(
        Long id,
        Type type,
        BigDecimal balance,
        OffsetDateTime createdAt
) {}
