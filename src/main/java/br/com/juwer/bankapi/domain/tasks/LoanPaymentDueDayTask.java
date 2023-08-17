package br.com.juwer.bankapi.domain.tasks;

import br.com.juwer.bankapi.domain.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LoanPaymentDueDayTask {

    private final AccountService accountService;
    private final String TIME_ZONE = "America/Sao_Paulo";
    @Scheduled(cron = "0 0 0 * * *", zone = TIME_ZONE)// once per day at 00:00
    public void execute(){
        accountService.payLoans();
    }
}
