package br.com.juwer.bankapi.api.controller;

import br.com.juwer.bankapi.api.dto.assembler.GroupAssembler;
import br.com.juwer.bankapi.api.dto.output.GroupDTO;
import br.com.juwer.bankapi.domain.repository.GroupRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/users/{userId}/groups")
@RequiredArgsConstructor
public class UserGroupsController {

    private final GroupRespository groupRespository;
    private final GroupAssembler groupAssembler;

    @GetMapping()
    public List<GroupDTO> getAllUserGroups(@PathVariable Long userId) {
        return groupAssembler.toCollectionModel(groupRespository.findAllGroupsByUserId(userId));
    }
}
