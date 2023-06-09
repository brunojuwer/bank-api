package br.com.juwer.bankapi.api.controller;

import br.com.juwer.bankapi.api.dto.assembler.PermissionAssembler;
import br.com.juwer.bankapi.api.dto.disassembler.PermissionDisassembler;
import br.com.juwer.bankapi.api.dto.input.PermissionDTOInput;
import br.com.juwer.bankapi.api.dto.output.PermissionDTO;
import br.com.juwer.bankapi.domain.model.Permission;
import br.com.juwer.bankapi.domain.repository.PermissionRepository;
import br.com.juwer.bankapi.domain.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/permissions")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionRepository permissionRepository;
    private final PermissionService permissionService;
    private final PermissionAssembler permissionAssembler;
    private final PermissionDisassembler permissionDisassembler;

    @GetMapping
    public List<PermissionDTO> getAllPermissions() {
        return permissionAssembler.toCollectionModel(permissionRepository.findAll());
    }

    @GetMapping("/{permissionId}")
    public PermissionDTO getPermission(@PathVariable Long permissionId) {
        Permission permission = permissionService.findPermissionById(permissionId);
        return permissionAssembler.toModel(permission);
    }

    @PutMapping("/{permissionId}")
    public PermissionDTO update(
        @PathVariable Long permissionId,
        @RequestBody PermissionDTOInput permissionDTOInput
    ) {
        Permission permissionWithNewData = permissionDisassembler.toDomainModel(permissionDTOInput);
        Permission permission = permissionService.findPermissionById(permissionId);
        return permissionAssembler.toModel(permissionService.update(permission, permissionWithNewData));
    }

    @PostMapping
    public PermissionDTO create(@RequestBody PermissionDTOInput permissionDto) {
        Permission permission = permissionDisassembler.toDomainModel(permissionDto);
        return permissionAssembler.toModel(permissionService.save(permission));
    }

    @DeleteMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long permissionId) {
        permissionService.delete(permissionId);
    }
}
