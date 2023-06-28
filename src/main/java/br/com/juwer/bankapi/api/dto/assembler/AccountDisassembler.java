package br.com.juwer.bankapi.api.dto.assembler;

import br.com.juwer.bankapi.api.dto.input.AccountInput;
import br.com.juwer.bankapi.domain.model.Account;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountDisassembler {

    private final ModelMapper mapper;

    public Account toDomainModel(AccountInput input) {
        return mapper.map(input, Account.class);
    }
}
