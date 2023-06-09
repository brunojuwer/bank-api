package br.com.juwer.bankapi.api.dto.assembler;

import br.com.juwer.bankapi.api.dto.output.GroupDTO;
import br.com.juwer.bankapi.api.dto.output.PermissionDTO;
import br.com.juwer.bankapi.domain.model.Group;
import br.com.juwer.bankapi.domain.model.Permission;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GroupAssembler {

    private final PermissionAssembler permissionAssembler;

    public GroupDTO toModel(Group group) {
        return new GroupDTO(
                group.getId(),
                group.getName(),
                this.toColletionDto(group.getPermissions())
        );
    }

    public List<GroupDTO> toCollectionModel(List<Group> groups) {
        return groups.stream()
                .map(this::toModel)
                .toList();
    }

    private Set<PermissionDTO> toColletionDto(Set<Permission> permissions) {
        return permissions.stream()
                .map(permissionAssembler::toModel)
                .collect(Collectors.toSet());
    }

}