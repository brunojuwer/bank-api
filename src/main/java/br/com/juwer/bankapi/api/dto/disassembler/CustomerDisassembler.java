package br.com.juwer.bankapi.api.dto.disassembler;

import br.com.juwer.bankapi.api.dto.input.CustomerInput;
import br.com.juwer.bankapi.domain.model.Customer;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerDisassembler extends GenericDisassembler<CustomerInput, Customer>{

    public CustomerDisassembler(ModelMapper mapper) {
        super(mapper);
    }
}
