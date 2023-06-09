package br.com.juwer.bankapi.api.dto.disassembler;

import br.com.juwer.bankapi.api.dto.input.PermissionDTOInput;
import br.com.juwer.bankapi.domain.model.Permission;
import org.springframework.stereotype.Component;

@Component
public class PermissionDisassembler {

    public Permission toDomainModel(PermissionDTOInput permissionDTO) {
        Permission permission  = new Permission();
        permission.setName(permissionDTO.name());
        permission.setDescription(permissionDTO.description());
        return permission;
    }
}
