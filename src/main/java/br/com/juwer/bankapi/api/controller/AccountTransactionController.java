package br.com.juwer.bankapi.api.controller;

import br.com.juwer.bankapi.api.dto.assembler.TransactionAssembler;
import br.com.juwer.bankapi.api.dto.disassembler.TransactionDisassembler;
import br.com.juwer.bankapi.api.dto.input.TransactionDTOInput;
import br.com.juwer.bankapi.api.dto.output.TransactionDTO;
import br.com.juwer.bankapi.config.security.verification.CheckSecurity;
import br.com.juwer.bankapi.domain.exceptions.SecurityValidationAccountException;
import br.com.juwer.bankapi.domain.model.Account;
import br.com.juwer.bankapi.domain.model.Transaction;
import br.com.juwer.bankapi.domain.model.Customer;
import br.com.juwer.bankapi.domain.service.AccountService;
import br.com.juwer.bankapi.domain.service.AccountTransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/accounts/{accountCode}/transaction")
@RequiredArgsConstructor
public class AccountTransactionController {

    private final AccountTransactionService accountTransactionService;
    private final TransactionDisassembler transactionDisassembler;
    private final TransactionAssembler transactionAssembler;

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CheckSecurity.Accounts.CanMakeTransfer
    public TransactionDTO depositOrWithdraw(
            @PathVariable String accountCode,
            @RequestBody @Valid TransactionDTOInput transactionDTOInput
    ) {
        Transaction transaction = transactionDisassembler.toDomainModel(transactionDTOInput);
        accountTransactionService.depositOrWithdraw(accountCode, transaction);
        return transactionAssembler.toModel(transaction);
    }
}
