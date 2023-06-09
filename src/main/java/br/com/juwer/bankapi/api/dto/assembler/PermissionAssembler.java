package br.com.juwer.bankapi.api.dto.assembler;

import br.com.juwer.bankapi.api.dto.output.PermissionDTO;
import br.com.juwer.bankapi.domain.model.Permission;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PermissionAssembler {

    public PermissionDTO toModel(Permission permission) {
        return new PermissionDTO(
                permission.getId(),
                permission.getName(),
                permission.getDescription()
        );
    }

    public List<PermissionDTO> toCollectionModel(List<Permission> permissions) {
        return permissions.stream()
                .map(this::toModel)
                .toList();
    }
}
