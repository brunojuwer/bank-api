package br.com.juwer.bankapi.api.controller;

import br.com.juwer.bankapi.api.dto.assembler.AccountInvestmentsDTOAssembler;
import br.com.juwer.bankapi.api.dto.input.TransactionDTOInput;
import br.com.juwer.bankapi.api.dto.output.AccountInvestmentsDTO;
import br.com.juwer.bankapi.domain.model.Account;
import br.com.juwer.bankapi.domain.model.AccountInvestmentRelationshipIDKeys;
import br.com.juwer.bankapi.domain.model.AccountInvestments;
import br.com.juwer.bankapi.domain.model.Investment;
import br.com.juwer.bankapi.domain.repository.AccountInvestmentsRepository;
import br.com.juwer.bankapi.domain.service.AccountInvestmentsService;
import br.com.juwer.bankapi.domain.service.AccountService;
import br.com.juwer.bankapi.domain.service.InvestmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts/{accountCode}/investments")
@RequiredArgsConstructor
public class AccountInvestmentsController {

    private final AccountInvestmentsRepository respository;
    private final AccountInvestmentsService service;
    private final AccountService accountService;
    private final InvestmentService investmentService;
    private final AccountInvestmentsDTOAssembler assembler;

//    @GetMapping("/{investmentId}")
//    public AccountInvestmentsDTO findOne(
//            @PathVariable Long investmentId,
//            @PathVariable String accountCode
//    ) {
//        AccountInvestments investment = service.findByCodes(accountCode, investmentId);
//        return assembler.toModel(investment);
//    }

    @PutMapping("/{investmentId}")
    public AccountInvestmentsDTO applicationOrRetrive(
            @PathVariable Long investmentId,
            @PathVariable String accountCode,
            @RequestBody @Valid TransactionDTOInput input
    ) {
        Account account = accountService.findByCode(accountCode);
        Investment investment = investmentService.findInvestmentById(investmentId);
        AccountInvestments investments = service.applicationOrReclaim(input, account, investment);

        return AccountInvestmentsDTO.builder()
                .code(investments.getCode())
                .investmentName(investment.getName())
                .totalBalance(investments.getTotalBalance())
                .createdAt(investments.getCreatedAt())
                .updatedAt(investments.getUpdatedAt())
                .build();
    }

}
