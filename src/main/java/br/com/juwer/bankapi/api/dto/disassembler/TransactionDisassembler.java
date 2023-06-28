package br.com.juwer.bankapi.api.dto.disassembler;

import br.com.juwer.bankapi.api.dto.input.TransactionDTOInput;
import br.com.juwer.bankapi.domain.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionDisassembler {

    private final ModelMapper mapper;

    public Transaction toDomainModel(TransactionDTOInput transactionDTOInput){
        return mapper.map(transactionDTOInput, Transaction.class);
    }
}
