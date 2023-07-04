package br.com.juwer.bankapi.api.dto.assembler;

import br.com.juwer.bankapi.api.dto.output.LoanTypeDTO;
import br.com.juwer.bankapi.domain.model.LoanType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LoanTypeDTOAssembler {

    private final ModelMapper mapper;

    public LoanTypeDTO toModel(LoanType loanType) {
        return mapper.map(loanType, LoanTypeDTO.class);
    }

    public List<LoanTypeDTO> toCollectionModel(List<LoanType> loanTypes) {
        return loanTypes.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
