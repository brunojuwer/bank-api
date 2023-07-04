package br.com.juwer.bankapi.api.controller;

import br.com.juwer.bankapi.api.dto.assembler.LoanTypeDTOAssembler;
import br.com.juwer.bankapi.api.dto.disassembler.LoanTypeInputDisassembler;
import br.com.juwer.bankapi.api.dto.input.LoanTypeInput;
import br.com.juwer.bankapi.api.dto.output.LoanTypeDTO;
import br.com.juwer.bankapi.domain.model.Loan;
import br.com.juwer.bankapi.domain.model.LoanType;
import br.com.juwer.bankapi.domain.repository.LoanTypeRepository;
import br.com.juwer.bankapi.domain.service.LoanTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loantypes")
@RequiredArgsConstructor
public class LoanTypeController {

    private final LoanTypeRepository repository;
    private final LoanTypeService service;
    private final LoanTypeDTOAssembler assembler;
    private final LoanTypeInputDisassembler disassembler;

    @GetMapping
    public List<LoanTypeDTO> findAll() {
        return assembler.toCollectionModel(repository.findAll());
    }

    @GetMapping("/{loanTypeId}")
    public LoanTypeDTO findOne(@PathVariable Long loanTypeId) {
        return assembler.toModel(service.findLoanTypeById(loanTypeId));
    }

    @PostMapping
    public LoanTypeDTO create(@Valid @RequestBody LoanTypeInput input){
        LoanType loanType = disassembler.toDomainModel(input);
        return assembler.toModel(service.create(loanType));
    }

    @PutMapping("/{loanId}")
    public LoanTypeDTO update(
            @PathVariable Long loanId,
            @RequestBody LoanTypeInput input
    ) {
        LoanType loanTypeToUpdate = service.findLoanTypeById(loanId);
        disassembler.copyToDomainModel(input, loanTypeToUpdate);
        return assembler.toModel(service.update(loanTypeToUpdate));
    }
}
