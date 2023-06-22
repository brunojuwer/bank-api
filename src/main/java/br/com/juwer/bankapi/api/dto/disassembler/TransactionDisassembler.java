package br.com.juwer.bankapi.api.dto.disassembler;

import br.com.juwer.bankapi.api.dto.input.TransactionDTOInput;
import br.com.juwer.bankapi.domain.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionDisassembler {

    public Transaction toDomainModel(TransactionDTOInput transactionDTOInput){
        return new Transaction(
                null,
                transactionDTOInput.ammount(),
                transactionDTOInput.operation(),
                null
        );
    }
}
