package br.com.juwer.bankapi.api.dto.disassembler;

import br.com.juwer.bankapi.api.dto.input.InvestmentInput;
import br.com.juwer.bankapi.domain.model.Investment;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class InvestmentDisassembler extends GenericDisassembler<InvestmentInput, Investment>{
    public InvestmentDisassembler(ModelMapper mapper) {
        super(mapper);
    }
}
