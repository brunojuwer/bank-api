package br.com.juwer.bankapi.domain.service;

import br.com.juwer.bankapi.domain.exceptions.PermissionNotFoundException;
import br.com.juwer.bankapi.domain.model.Permission;
import br.com.juwer.bankapi.domain.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PermissionService {

    private final PermissionRepository permissionRepository;

    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }

    public Permission findPermissionById(Long permissionId) {
        return permissionRepository.findById(permissionId)
                .orElseThrow(() -> new PermissionNotFoundException(permissionId));
    }
}
