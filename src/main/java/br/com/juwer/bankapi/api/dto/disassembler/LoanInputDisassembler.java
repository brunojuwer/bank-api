package br.com.juwer.bankapi.api.dto.disassembler;

import br.com.juwer.bankapi.api.dto.input.LoanInput;
import br.com.juwer.bankapi.domain.model.Loan;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LoanInputDisassembler extends GenericDisassembler<LoanInput, Loan> {
    public LoanInputDisassembler(ModelMapper mapper) {
        super(mapper);
    }
}
