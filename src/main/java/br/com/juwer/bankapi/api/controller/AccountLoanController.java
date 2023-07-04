package br.com.juwer.bankapi.api.controller;

import br.com.juwer.bankapi.api.dto.assembler.LoanDTOAssembler;
import br.com.juwer.bankapi.api.dto.disassembler.LoanInputDisassembler;
import br.com.juwer.bankapi.api.dto.input.LoanInput;
import br.com.juwer.bankapi.api.dto.output.LoanDTO;
import br.com.juwer.bankapi.domain.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts/{accountCode}/loans")
@RequiredArgsConstructor
public class AccountLoanController {

    private LoanService service;
    private LoanDTOAssembler assembler;
    private LoanInputDisassembler disassembler;

    @PostMapping
    public LoanDTO create(
            @PathVariable String accountCode,
            @RequestBody LoanInput loanInput
    ) {


        return null;
    }
}
