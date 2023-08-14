package br.com.juwer.bankapi.api.controller;

import br.com.juwer.bankapi.api.dto.assembler.LoanDTOAssembler;
import br.com.juwer.bankapi.api.dto.disassembler.LoanInputDisassembler;
import br.com.juwer.bankapi.api.dto.input.LoanInput;
import br.com.juwer.bankapi.api.dto.output.LoanDTO;
import br.com.juwer.bankapi.config.security.verification.CheckSecurity;
import br.com.juwer.bankapi.domain.model.Account;
import br.com.juwer.bankapi.domain.model.Loan;
import br.com.juwer.bankapi.domain.service.AccountService;
import br.com.juwer.bankapi.domain.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts/{accountCode}/loans")
@RequiredArgsConstructor
public class AccountLoanController {

    private final LoanService service;
    private final AccountService accountService;
    private final LoanInputDisassembler disassembler;
    private final LoanDTOAssembler assembler;


    @PostMapping
    public LoanDTO create(
            @PathVariable String accountCode,
            @RequestBody LoanInput loanInput
    ) {
        Account account = accountService.findByCode(accountCode);
        Loan loan = service.create(disassembler.toDomainModel(loanInput));
        account.addLoan(loan);
        accountService.update(account);
        return assembler.toModel(loan);
    }

    @GetMapping
    @CheckSecurity.Accounts.OwnsAccount
    public List<LoanDTO> findAll(@PathVariable String accountCode) {
        return assembler.toCollectionModel(service.findAllByAccountCode(accountCode));
    }

    @GetMapping("/{loanId}")
    public LoanDTO findOne(
            @PathVariable String accountCode,
            @PathVariable Long loanId
    ) {
        return assembler.toModel(service.findByAccountCodeAndLoanId(accountCode, loanId));
    }


}