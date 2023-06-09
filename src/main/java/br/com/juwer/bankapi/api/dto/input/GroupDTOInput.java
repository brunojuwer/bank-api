package br.com.juwer.bankapi.api.dto.input;

import java.util.Set;

public record GroupDTOInput(String name, Set<Long> permissions) {}
