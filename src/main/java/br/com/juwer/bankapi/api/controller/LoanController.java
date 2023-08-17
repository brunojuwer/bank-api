package br.com.juwer.bankapi.api.controller;

import br.com.juwer.bankapi.api.dto.assembler.LoanDTOAssembler;
import br.com.juwer.bankapi.api.dto.disassembler.LoanInputDisassembler;
import br.com.juwer.bankapi.api.dto.input.LoanPaymentInput;
import br.com.juwer.bankapi.api.dto.output.LoanDTO;
import br.com.juwer.bankapi.domain.model.Loan;
import br.com.juwer.bankapi.domain.repository.LoanRepository;
import br.com.juwer.bankapi.domain.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService service;
    private final LoanRepository repository;
    private final LoanInputDisassembler disassembler;
    private final LoanDTOAssembler assembler;


    @GetMapping
    public List<LoanDTO> findAll() {
        return assembler.toCollectionModel(repository.findAll());
    }

    @GetMapping("/{loanId}")
    public LoanDTO findAll(@PathVariable Long loanId) {
        return assembler.toModel(service.findById(loanId));
    }

    @PutMapping("/{loanId}/deny")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void denyLoan(@PathVariable Long loanId) {
        service.denyLoan(loanId);
    }

    @PutMapping("/{loanId}/approve")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void approveLoan(
            @PathVariable Long loanId,
            @RequestBody LoanPaymentInput loan
            ) {
        service.approveLoan(loanId, loan);
    }
}
