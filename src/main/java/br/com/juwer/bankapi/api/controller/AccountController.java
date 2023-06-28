package br.com.juwer.bankapi.api.controller;

import br.com.juwer.bankapi.api.dto.assembler.AccountAssembler;
import br.com.juwer.bankapi.api.dto.assembler.AccountDisassembler;
import br.com.juwer.bankapi.api.dto.disassembler.AddressDisassembler;
import br.com.juwer.bankapi.api.dto.disassembler.CustomerDisassembler;
import br.com.juwer.bankapi.api.dto.input.AccountInput;
import br.com.juwer.bankapi.api.dto.output.AccountDTO;
import br.com.juwer.bankapi.domain.model.Account;
import br.com.juwer.bankapi.domain.model.Address;
import br.com.juwer.bankapi.domain.model.Customer;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTO create(@RequestBody @Valid AccountInput input) {
        Account account = accountDisassembler.toDomainModel(input);
        return accountAssembler.toModel(accountService.save(account));
    }
}
