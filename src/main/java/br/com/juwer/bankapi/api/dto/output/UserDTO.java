package br.com.juwer.bankapi.api.dto.output;

import br.com.juwer.bankapi.domain.model.Group;

import java.util.Set;

public record UserDTO(
        Long id,
        String fullName,
        String email,
        Set<Group> groups
) {}
