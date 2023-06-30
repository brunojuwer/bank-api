package br.com.juwer.bankapi.api.dto.assembler;

import br.com.juwer.bankapi.api.dto.output.AccountInvestmentsDTO;
import br.com.juwer.bankapi.domain.model.AccountInvestments;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AccountInvestmentsDTOAssembler {

    private final ModelMapper mapper;

    public AccountInvestmentsDTO toModel(AccountInvestments accountInvestments) {
        return mapper.map(accountInvestments, AccountInvestmentsDTO.class);
    }

    public List<AccountInvestmentsDTO> toCollectionModel(List<AccountInvestments> investments) {
        return investments.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
