package br.com.juwer.bankapi.domain.service;

import br.com.juwer.bankapi.domain.exceptions.PermissionNotFoundException;
import br.com.juwer.bankapi.domain.model.Permission;
import br.com.juwer.bankapi.domain.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PermissionService {

    private final PermissionRepository permissionRepository;

    @Transactional
    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Transactional
    public Permission update(Permission permission, Permission permissionWithNewData) {
        permission.setName(permissionWithNewData.getName());
        permission.setDescription(permissionWithNewData.getDescription());
        return permission;
    }

    @Transactional
    public void delete(Long permissionId) {
        permissionRepository.deleteById(permissionId);
    }

    public Permission findPermissionById(Long permissionId) {
        return permissionRepository.findById(permissionId)
                .orElseThrow(() -> new PermissionNotFoundException(permissionId));
    }
}
