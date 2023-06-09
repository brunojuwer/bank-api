package br.com.juwer.bankapi.domain.exceptions;

public class PermissionNotFoundException extends RuntimeException {
    public PermissionNotFoundException(String message) {
        super(message);
    }

    public PermissionNotFoundException(Long permissionId) {
        this(String.format("Permission with id %d not found", permissionId));
    }
}
