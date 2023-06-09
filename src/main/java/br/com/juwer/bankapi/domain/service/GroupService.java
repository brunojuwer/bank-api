package br.com.juwer.bankapi.domain.service;

import br.com.juwer.bankapi.domain.exceptions.GroupNotFoundException;
import br.com.juwer.bankapi.domain.model.Group;
import br.com.juwer.bankapi.domain.repository.GroupRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRespository groupRespository;

    @Transactional
    public Group save(Group group) {
        return groupRespository.save(group);
    }

    @Transactional
    public Group update(Group group, Group groupWithNewData) {
        group.setName(groupWithNewData.getName());
        group.setPermissions(groupWithNewData.getPermissions());
        return group;
    }

    @Transactional
    public void delete(Long groupId) {
        this.findGroupById(groupId);
        groupRespository.deleteById(groupId);
    }

    public Group findGroupById(Long groupId) {
        return groupRespository.findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException(groupId));
    }
}
