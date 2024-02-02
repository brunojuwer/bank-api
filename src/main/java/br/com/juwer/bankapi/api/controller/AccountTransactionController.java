package br.com.juwer.bankapi.api.controller;

import br.com.juwer.bankapi.api.dto.assembler.TransactionAssembler;
import br.com.juwer.bankapi.api.dto.output.TransactionDTO;
import br.com.juwer.bankapi.api.dto.request.DepositRequest;
import br.com.juwer.bankapi.api.dto.request.WithdrawRequest;
import br.com.juwer.bankapi.config.security.verification.CheckSecurity;
import br.com.juwer.bankapi.domain.model.Transaction;
import br.com.juwer.bankapi.domain.service.AccountTransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/accounts/{accountCode}/transactions")
@RequiredArgsConstructor
public class AccountTransactionController {

    private final AccountTransactionService accountTransactionService;
    private final TransactionAssembler transactionAssembler;

    @PutMapping("/deposit")
    @ResponseStatus(HttpStatus.CREATED)
    @CheckSecurity.Accounts.CanMakeTransfer
    public TransactionDTO deposit(
            @PathVariable String accountCode,
            @RequestBody @Valid DepositRequest depositRequest
    ) {
        Transaction transaction = accountTransactionService.deposit(accountCode, depositRequest.getAmount());
        return transactionAssembler.toModel(transaction);
    }

    @PutMapping("/withdraw")
    @ResponseStatus(HttpStatus.CREATED)
    @CheckSecurity.Accounts.CanMakeTransfer
    public TransactionDTO withdraw(
            @PathVariable String accountCode,
            @RequestBody @Valid WithdrawRequest withdrawRequest
    ) {
        Transaction transaction = accountTransactionService.withdraw(accountCode, withdrawRequest.getAmount());
        return transactionAssembler.toModel(transaction);
    }

    @GetMapping
    @CheckSecurity.Accounts.OwnsAccount
    public List<TransactionDTO> findAll(@PathVariable String accountCode){
        return transactionAssembler.toCollectionModel(accountTransactionService.findAll(accountCode));
    }
}