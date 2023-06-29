package br.com.juwer.bankapi.api.dto.assembler;

import br.com.juwer.bankapi.api.dto.output.InvestmentDTO;
import br.com.juwer.bankapi.domain.model.Investment;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InvestmentAssembler {

    private final ModelMapper mapper;

    public InvestmentDTO toModel(Investment investment) {
        return mapper.map(investment, InvestmentDTO.class);
    }

    public List<InvestmentDTO> toCollectionModel(List<Investment> investments) {
        return investments.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
