package br.com.juwer.bankapi.api.controller;

import br.com.juwer.bankapi.api.dto.disassembler.GroupDisassembler;
import br.com.juwer.bankapi.api.dto.assembler.GroupAssembler;
import br.com.juwer.bankapi.api.dto.input.GroupDTOInput;
import br.com.juwer.bankapi.api.dto.output.GroupDTO;
import br.com.juwer.bankapi.domain.model.Group;
import br.com.juwer.bankapi.domain.repository.GroupRespository;
import br.com.juwer.bankapi.domain.service.GroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupRespository groupRespository;
    private final GroupService groupService;
    private final GroupAssembler groupAssembler;
    private final GroupDisassembler groupDisassembler;

    @GetMapping
    public List<GroupDTO> getAll() {
        return groupAssembler.toCollectionModel(groupRespository.findAll());
    }

    @GetMapping("/{groupId}")
    public GroupDTO find(@PathVariable Long groupId) {
        return groupAssembler.toModel(groupService.findGroupById(groupId));
    }

    @PostMapping
    public GroupDTO create(@Valid @RequestBody GroupDTOInput groupDTOInput){
        Group group = groupDisassembler.toDomainModel(groupDTOInput);
        return groupAssembler.toModel(groupService.save(group));
    }

    @PutMapping("/{groupId}")
    public GroupDTO update(
        @PathVariable Long groupId,
        @Valid @RequestBody GroupDTOInput groupDTOInput
    ) {
        Group group = groupService.findGroupById(groupId);
        Group groupWithNewData = groupDisassembler.toDomainModel(groupDTOInput);
        return groupAssembler.toModel(groupService.update(group, groupWithNewData));
    }

    @DeleteMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long groupId) {
        groupService.delete(groupId);
    }

}