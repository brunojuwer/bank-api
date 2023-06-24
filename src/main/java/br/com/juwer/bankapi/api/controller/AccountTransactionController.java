package br.com.juwer.bankapi.api.controller;

import br.com.juwer.bankapi.api.dto.assembler.TransactionAssembler;
import br.com.juwer.bankapi.api.dto.disassembler.TransactionDisassembler;
import br.com.juwer.bankapi.api.dto.input.TransactionDTOInput;
import br.com.juwer.bankapi.api.dto.output.AccountDTO;
import br.com.juwer.bankapi.api.dto.output.TransactionDTO;
import br.com.juwer.bankapi.domain.model.Account;
import br.com.juwer.bankapi.domain.model.Transaction;
import br.com.juwer.bankapi.domain.model.User;
import br.com.juwer.bankapi.domain.service.AccountService;
import br.com.juwer.bankapi.domain.service.AccountTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/accounts/{accountId}/transaction")
@RequiredArgsConstructor
public class AccountTransactionController {

    private final AccountTransactionService accountTransactionService;
    private final AccountService accountService;
    private final TransactionDisassembler transactionDisassembler;
    private final TransactionAssembler transactionAssembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionDTO deposit(
            @PathVariable Long accountId,
            @RequestBody TransactionDTOInput transactionDTOInput
    ) {
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Account account = accountService.findAccountByOwnIdAndUserId(accountId, authenticatedUser.getId());
        Transaction transaction = transactionDisassembler.toDomainModel(transactionDTOInput);

        Transaction savedTransaction = accountTransactionService.deposit(account, transaction);
        return transactionAssembler.toModel(savedTransaction);
    }
}
