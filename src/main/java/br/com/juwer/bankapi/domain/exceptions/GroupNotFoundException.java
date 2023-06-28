package br.com.juwer.bankapi.domain.exceptions;

public class GroupNotFoundException extends EntityNotFoundException {

    public GroupNotFoundException(String message) {
        super(message);
    }

    public GroupNotFoundException(Long groupId) {
        this(String.format("Group with id %d not found", groupId));
    }
}
