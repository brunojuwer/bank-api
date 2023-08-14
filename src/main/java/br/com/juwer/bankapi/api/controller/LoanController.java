package br.com.juwer.bankapi.api.controller;

import br.com.juwer.bankapi.api.dto.assembler.LoanDTOAssembler;
import br.com.juwer.bankapi.api.dto.disassembler.LoanInputDisassembler;
import br.com.juwer.bankapi.domain.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService service;
    private final LoanInputDisassembler disassembler;
    private final LoanDTOAssembler assembler;

    @PutMapping("/{loanId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void denyLoan(@PathVariable Long loanId) {
        service.denyLoan(loanId);
    }
}
