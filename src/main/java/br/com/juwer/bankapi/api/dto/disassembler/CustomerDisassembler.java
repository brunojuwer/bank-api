package br.com.juwer.bankapi.api.dto.disassembler;

import br.com.juwer.bankapi.api.dto.input.CustomerInput;
import br.com.juwer.bankapi.domain.model.Customer;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerDisassembler {

    private final ModelMapper mapper;

    public Customer toDomainModel(CustomerInput input) {
        return mapper.map(input, Customer.class);
    }
}
