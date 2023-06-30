package br.com.juwer.bankapi.api.controller;

import br.com.juwer.bankapi.api.dto.assembler.AccountAssembler;
import br.com.juwer.bankapi.api.dto.assembler.AccountDTOResumeAssembler;
import br.com.juwer.bankapi.api.dto.assembler.AccountDisassembler;
import br.com.juwer.bankapi.api.dto.input.AccountInput;
import br.com.juwer.bankapi.api.dto.input.AccountInputPassword;
import br.com.juwer.bankapi.api.dto.output.AccountDTO;
import br.com.juwer.bankapi.api.dto.output.AccountDTOResume;
import br.com.juwer.bankapi.config.security.verification.CheckSecurity;
import br.com.juwer.bankapi.domain.model.Account;
import br.com.juwer.bankapi.domain.projections.AccountResume;
import br.com.juwer.bankapi.domain.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final AccountAssembler accountAssembler;
    private final AccountDisassembler accountDisassembler;

    @GetMapping(value = "/{accountCode}", params = "projection=resume")
    @CheckSecurity.Accounts.OwsAccount
    public AccountResume findResumedAccount(@PathVariable String accountCode) {
        return accountService.findResumeByCode(accountCode);
    }

    @GetMapping("/{accountCode}")
    public AccountDTO findAccount(@PathVariable String accountCode) {
        return accountAssembler.toModel(accountService.findByCodeWithCustomer(accountCode));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTO create(@RequestBody @Valid AccountInput input) {
        Account account = accountDisassembler.toDomainModel(input);
        return accountAssembler.toModel(accountService.save(account));
    }

    @PutMapping("/{accountCode}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CheckSecurity.Accounts.OwsAccount
    public void updatePassword(
            @PathVariable String accountCode,
            @Valid @RequestBody AccountInputPassword accountInputPassword
    ){
        Account account = accountService.findByCode(accountCode);
        accountService.updatePassword(account, accountInputPassword);
    }
}
