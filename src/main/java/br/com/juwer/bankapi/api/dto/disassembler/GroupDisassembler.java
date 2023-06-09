package br.com.juwer.bankapi.api.dto.disassembler;

import br.com.juwer.bankapi.api.dto.input.GroupDTOInput;
import br.com.juwer.bankapi.domain.model.Group;
import br.com.juwer.bankapi.domain.model.Permission;
import br.com.juwer.bankapi.domain.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GroupDisassembler {

    private final PermissionService permissionService;

    public Group toDomainModel(GroupDTOInput groupDTOInput) {
        Group group = new Group();
        group.setName(groupDTOInput.name());
        group.setPermissions(this.toColletionDomainModel(groupDTOInput.permissions()));
        return group;
    }

    public Set<Permission> toColletionDomainModel(Set<Long> permissionDTOS) {
        return permissionDTOS.stream()
                .map(permissionService::findPermissionById)
                .collect(Collectors.toSet());
    }
}
