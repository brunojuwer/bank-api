package br.com.juwer.bankapi.api.dto.assembler;

import br.com.juwer.bankapi.api.dto.output.TransactionDTO;
import br.com.juwer.bankapi.domain.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TransactionAssembler {

    private final ModelMapper mapper;

    public TransactionDTO toModel(Transaction transaction) {
        return mapper.map(transaction, TransactionDTO.class);
    }

    public List<TransactionDTO> toCollectionModel(List<Transaction> transactions) {
        return transactions.stream()
                .map(this::toModel)
                .toList();
    }
}
