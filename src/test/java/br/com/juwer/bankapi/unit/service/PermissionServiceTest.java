package br.com.juwer.bankapi.unit.service;

import br.com.juwer.bankapi.domain.exceptions.PermissionNotFoundException;
import br.com.juwer.bankapi.domain.model.Permission;
import br.com.juwer.bankapi.domain.repository.PermissionRepository;
import br.com.juwer.bankapi.domain.service.PermissionService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PermissionServiceTest {

    @InjectMocks
    PermissionService permissionService;

    @Mock
    PermissionRepository permissionRepository;

    @Test
    void itShouldSavePermissionWithSuccess() {
        Permission permission = createPermission();

        when(permissionRepository.save(permission)).thenReturn(permission);
        permissionService.save(permission);

        verify(permissionRepository).save(permission);
    }

    @Test
    void itShouldUpdateAnAccountWithSuccess() {
        Permission existingPermission = createPermissionWithId();
        Permission permissionWithNewData = createPermissionWithNewData();

        when(permissionRepository.save(existingPermission)).thenReturn(existingPermission);
        Permission updatedPermission = permissionService.update(existingPermission, permissionWithNewData);

        verify(permissionRepository).save(existingPermission);
        assertEquals(permissionWithNewData.getName(), updatedPermission.getName());
        assertEquals(permissionWithNewData.getDescription(), updatedPermission.getDescription());
    }

    @Test
    void itShouldDeletePermissionWithSuccess() {
        Permission permission = createPermissionWithId();
        Long permissionId = permission.getId();

        when(permissionRepository.findById(permissionId)).thenReturn(Optional.of(permission));
        permissionService.delete(permissionId);

        verify(permissionRepository).deleteById(permissionId);
    }

    @Test
    void itShouldThrowPermissionNotFoundExceptionWhenDeletePermissionWithNonexistentId() {
        Permission permission = createPermissionWithId();
        Long permissionId = permission.getId();

        when(permissionRepository.findById(permissionId)).thenReturn(Optional.empty());
        Throwable exception = catchThrowable(() -> permissionService.delete(permissionId));

        verify(permissionRepository, never()).deleteById(permissionId);
        assertEquals("Permission with id 1 not found", exception.getMessage());
    }

    @Test
    void itShouldfindPermissionById() {
        Permission permission = createPermissionWithId();
        Long permissionId = permission.getId();

        when(permissionRepository.findById(permissionId)).thenReturn(Optional.of(permission));
        Permission foundPermission = permissionService.findPermissionById(permissionId);

        verify(permissionRepository).findById(permissionId);
        assertEquals(foundPermission, permission);
    }

    @Test
    void itShouldThrowPermissionNotFoundException() {
        Permission permission = createPermissionWithId();
        Long permissionId = permission.getId();

        when(permissionRepository.findById(permissionId)).thenReturn(Optional.empty());
        Throwable exception = catchThrowable(() -> permissionService.findPermissionById(permissionId));

        assertEquals("Permission with id 1 not found", exception.getMessage());
    }

    private static Permission createPermission() {
        return new Permission().builder()
                .name("READ_ACCOUNT")
                .description("Can read all information of account")
                .build();
    }

    private static Permission createPermissionWithId() {
        return new Permission().builder()
                .id(1L)
                .name("READ_ACCOUNT")
                .description("Can read all information of account")
                .build();
    }

    private static Permission createPermissionWithNewData() {
        return new Permission().builder()
                .id(1L)
                .name("READ_ACCOUNT")
                .description("Can read the information of account")
                .build();
    }
}