package br.com.juwer.bankapi.api.dto.assembler;

import br.com.juwer.bankapi.api.dto.output.LoanDTO;
import br.com.juwer.bankapi.domain.model.Loan;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LoanDTOAssembler {

    private final ModelMapper mapper;

    public LoanDTO toModel(Loan loan) {
        return mapper.map(loan, LoanDTO.class);
    }

    public List<LoanDTO> toCollectionModel(List<Loan> loans) {
        return loans.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
