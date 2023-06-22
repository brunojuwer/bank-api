package br.com.juwer.bankapi.api.dto.assembler;

import br.com.juwer.bankapi.api.dto.output.TransactionDTO;
import br.com.juwer.bankapi.domain.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionAssembler {

    public TransactionDTO toModel(Transaction transaction) {
        return new TransactionDTO(
                transaction.getId(),
                transaction.getAmmount(),
                transaction.getOperation(),
                transaction.getCreatedAt()
        );
    }
}
