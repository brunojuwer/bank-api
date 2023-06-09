package br.com.juwer.bankapi.api.dto.output;

import java.util.Set;

public record GroupDTO(Long id, String name, Set<PermissionDTO> permissions) {}
