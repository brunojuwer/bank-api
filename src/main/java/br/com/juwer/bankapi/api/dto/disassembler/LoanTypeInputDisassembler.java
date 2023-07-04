package br.com.juwer.bankapi.api.dto.disassembler;

import br.com.juwer.bankapi.api.dto.input.LoanTypeInput;
import br.com.juwer.bankapi.domain.model.LoanType;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LoanTypeInputDisassembler extends GenericDisassembler<LoanTypeInput, LoanType> {
    public LoanTypeInputDisassembler(ModelMapper mapper) {
        super(mapper);
    }
}
