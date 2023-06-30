package br.com.juwer.bankapi.api.dto.assembler;

import br.com.juwer.bankapi.api.dto.output.AccountDTOResume;
import br.com.juwer.bankapi.domain.model.Account;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountDTOResumeAssembler {

    private final ModelMapper mapper;

    public AccountDTOResume toModel(Account account) {
        return mapper.map(account, AccountDTOResume.class);
    }
}
