package br.com.juwer.bankapi.domain.service;

import br.com.juwer.bankapi.api.dto.input.TransactionDTOInput;
import br.com.juwer.bankapi.domain.exceptions.AccountInvestmentNotFoundException;
import br.com.juwer.bankapi.domain.exceptions.InsufficientBalanceException;
import br.com.juwer.bankapi.domain.exceptions.InvalidTransactionException;
import br.com.juwer.bankapi.domain.model.*;
import br.com.juwer.bankapi.domain.projections.AccountInvestmentResume;
import br.com.juwer.bankapi.domain.repository.AccountInvestmentsRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.juwer.bankapi.domain.model.Transaction.Operation.APPLICATION;
import static br.com.juwer.bankapi.domain.model.Transaction.Operation.RECLAIM;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class AccountInvestmentsService {

    private final AccountInvestmentsRepository repository;
    private final AccountTransactionService accountTransactionService;
    private final AccountService accountService;
    private final InvestmentService investmentService;


    public AccountInvestmentResume findAccountInvestment(String accountCode, Long investmemtId){
        AccountInvestments investment = repository.findByIds(accountCode, investmemtId)
                .orElseThrow(() ->
                        new AccountInvestmentNotFoundException("Account investment not found")
                );
        return new AccountInvestmentResume(
                        investment.getInvestment().getId(),
                        investment.getCode(),
                        investment.getTotalBalance(),
                        investment.getInvestment().getName()
                    );
    }

    public List<AccountInvestmentResume> findAccountInvestment(String accountCode){
        return repository.findAll(accountCode)
                .orElseThrow(() ->
                        new AccountInvestmentNotFoundException("Account investment not found")
                ).stream()
                 .map(investment -> new AccountInvestmentResume(
                            investment.getInvestment().getId(),
                            investment.getCode(),
                            investment.getTotalBalance(),
                            investment.getInvestment().getName()))
                .collect(Collectors.toList());
    }

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
                    if(this.isReclaimValueMoreThanInvestmentHave(input.getAmount(), accountInvestment)){
                        throw new InsufficientBalanceException(
                                format("The maximum amount to reclaim is: %s ", accountInvestment.getTotalBalance())
                        );
                    }
                    makeReclaim(account, accountInvestment, investment, transaction, input);
                }
                case APPLICATION -> {
                    makeApplication(account, accountInvestment, investment, transaction, input);
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

    private boolean isReclaimValueMoreThanInvestmentHave(BigDecimal amount, @NotNull AccountInvestments investment) {
        return investment.getTotalBalance().compareTo(amount) < 0;
    }

    private void makeReclaim(
            Account account,
            AccountInvestments accountInvestment,
            Investment investment,
            Transaction transaction,
            TransactionDTOInput input

    ){
        account.addToBalance(input.getAmount());
        accountInvestment.reclaim(input.getAmount());
        investment.subtractBalance(input.getAmount());
        transaction.setOperation(RECLAIM);
    }

    private void makeApplication(
            Account account,
            AccountInvestments accountInvestment,
            Investment investment,
            Transaction transaction,
            TransactionDTOInput input
    ) {
        account.subtractBalance(input.getAmount());
        accountInvestment.application(input.getAmount());
        investment.addToBalance(input.getAmount());
        transaction.setOperation(APPLICATION);
    }
}
