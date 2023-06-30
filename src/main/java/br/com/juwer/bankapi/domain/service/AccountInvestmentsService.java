package br.com.juwer.bankapi.domain.service;

import br.com.juwer.bankapi.api.dto.input.TransactionDTOInput;
import br.com.juwer.bankapi.domain.exceptions.AccountInvestmentNotFoundException;
import br.com.juwer.bankapi.domain.exceptions.InvalidTransactionException;
import br.com.juwer.bankapi.domain.model.*;
import br.com.juwer.bankapi.domain.repository.AccountInvestmentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static br.com.juwer.bankapi.domain.model.Transaction.Operation.APPLICATION;
import static br.com.juwer.bankapi.domain.model.Transaction.Operation.RECLAIM;

@Service
@RequiredArgsConstructor
public class AccountInvestmentsService {

    private final AccountInvestmentsRepository repository;
    private final AccountTransactionService accountTransactionService;
    private final AccountService accountService;
    private final InvestmentService investmentService;

    @Transactional
    public AccountInvestments applicationOrReclaim(
            TransactionDTOInput input,
            Account account,
            Investment investment
    ) {
        AccountInvestments accountInvestment = getAccountInvestments(account, investment);
        boolean isANewInvestmentApplication = accountInvestment.getCode() == null;

        Transaction transaction = new Transaction();
        transaction.setProduct(investment.getName());
        transaction.setAccountCode(account.getCode());
        transaction.setAmount(input.getAmount());

        if(isANewInvestmentApplication && input.getOperation().equals(APPLICATION)) {
            account.subtractBalance(input.getAmount());
            accountInvestment.setTotalBalance(input.getAmount());
            accountInvestment.setInvestment(investment);
            accountInvestment.setAccount(account);

            investment.addToBalance(input.getAmount());
            transaction.setOperation(APPLICATION);
        } else if (isANewInvestmentApplication && input.getOperation().equals(RECLAIM)) {
            throw new InvalidTransactionException(
                "You cannot RECLAIM this product because you do not have applied any amount yet"
            );
        }

        if (!isANewInvestmentApplication) {
            switch (input.getOperation()) {
                case RECLAIM -> {
                    account.addToBalance(input.getAmount());
                    accountInvestment.reclaim(input.getAmount());
                    investment.subtractBalance(input.getAmount());
                    transaction.setOperation(RECLAIM);
                }
                case APPLICATION -> {
                    account.subtractBalance(input.getAmount());
                    accountInvestment.application(input.getAmount());
                    investment.addToBalance(input.getAmount());
                    transaction.setOperation(APPLICATION);
                }
                default -> {
                }
            }
        }

        accountService.update(account);
        investmentService.update(investment);
        accountTransactionService.create(transaction);
        return repository.save(accountInvestment);
    }

    private AccountInvestments getAccountInvestments(Account account, Investment investment) {
        var relationshipIDKeys = new AccountInvestmentRelationshipIDKeys(account, investment);
        Optional<AccountInvestments> investmentAc = repository.findById(relationshipIDKeys);

        return investmentAc.orElseGet(AccountInvestments::new);
    }

    public AccountInvestments findAccountInvesment(AccountInvestmentRelationshipIDKeys keys){
        return repository.findById(keys)
                .orElseThrow(() ->
                    new AccountInvestmentNotFoundException("Account investment not found")
                );
    }
}
