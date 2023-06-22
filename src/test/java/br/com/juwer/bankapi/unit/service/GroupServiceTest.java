package br.com.juwer.bankapi.unit.service;

import br.com.juwer.bankapi.domain.model.Group;
import br.com.juwer.bankapi.domain.repository.GroupRespository;
import br.com.juwer.bankapi.domain.service.GroupService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GroupServiceTest {

    @InjectMocks
    GroupService groupService;

    @Mock
    GroupRespository groupRespository;

    @Test
    void itShouldSaveGroupWithSuccess() {
        Group group = createGroup();

        when(groupService.save(group)).thenReturn(group);
        groupService.save(group);

        verify(groupRespository).save(group);
    }

    @Test
    void itShouldUpdateAnGroupWithSuccess() {
        Group existingGroup = createGroupWithId();
        Group groupWithNewData = createGroupWithNewData();

        when(groupRespository.save(existingGroup)).thenReturn(existingGroup);
        Group updatedGroup = groupService.update(existingGroup, groupWithNewData);

        verify(groupRespository).save(existingGroup);
        assertEquals(groupWithNewData.getName(), updatedGroup.getName());
    }

    @Test
    void itShouldDeleteGroupWithSuccess() {
        Group group = createGroupWithId();
        Long groupId = group.getId();

        when(groupRespository.findById(groupId)).thenReturn(Optional.of(group));
        groupService.delete(groupId);

        verify(groupRespository).deleteById(groupId);
    }

    @Test
    void itShouldThrowGroupNotFoundExceptionWhenDeleteGroupWithNonexistentId() {
        Group group = createGroupWithId();
        Long groupId = group.getId();

        when(groupRespository.findById(groupId)).thenReturn(Optional.empty());
        Throwable exception = catchThrowable(() -> groupService.delete(groupId));

        verify(groupRespository, never()).deleteById(groupId);
        assertEquals("Group with id 1 not found", exception.getMessage());
    }

    @Test
    void itShouldfindGroupById() {
        Group group = createGroupWithId();
        Long groupId = group.getId();

        when(groupRespository.findById(groupId)).thenReturn(Optional.of(group));
        Group foundGroup = groupService.findGroupById(groupId);

        verify(groupRespository).findById(groupId);
        assertEquals(foundGroup, group);
    }

    @Test
    void itShouldThrowGroupNotFoundException() {
        Group group = createGroupWithId();
        Long groupId = group.getId();

        when(groupRespository.findById(groupId)).thenReturn(Optional.empty());
        Throwable exception = catchThrowable(() -> groupService.findGroupById(groupId));

        assertEquals("Group with id 1 not found", exception.getMessage());
    }

    private static Group createGroup() {
        return new Group().builder()
                .name("CLIENT")
                .build();
    }

    private static Group createGroupWithId() {
        return new Group().builder()
                .id(1L)
                .name("CLIENT")
                .build();
    }

    private static Group createGroupWithNewData() {
        return new Group().builder()
                .id(1L)
                .name("CLIENT:BANK")
                .build();
    }

}