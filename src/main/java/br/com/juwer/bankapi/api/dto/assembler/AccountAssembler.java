package br.com.juwer.bankapi.api.dto.assembler;

import br.com.juwer.bankapi.api.dto.output.AccountDTO;
import br.com.juwer.bankapi.domain.model.Account;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountAssembler {

    private final ModelMapper mapper;

    public AccountDTO toModel(Account account) {
        return mapper.map(account, AccountDTO.class);
    }
}
