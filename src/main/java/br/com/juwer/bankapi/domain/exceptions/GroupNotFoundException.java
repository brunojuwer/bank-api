package br.com.juwer.bankapi.domain.exceptions;

public class GroupNotFoundException extends RuntimeException {

    public GroupNotFoundException(String message) {
        super(message);
    }

    public GroupNotFoundException(Long groupId) {
        this(String.format("Group with Id %d not found", groupId));
    }
}
