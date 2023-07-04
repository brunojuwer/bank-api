package br.com.juwer.bankapi.domain.service;

import br.com.juwer.bankapi.api.dto.output.LoanTypeDTO;
import br.com.juwer.bankapi.domain.exceptions.LoanTypeNotFoundException;
import br.com.juwer.bankapi.domain.model.LoanType;
import br.com.juwer.bankapi.domain.repository.LoanTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoanTypeService {

    private final LoanTypeRepository repository;

    public LoanType create(LoanType loanType) {
        return repository.save(loanType);
    }

    public LoanType update(LoanType loanTypeToUpdate) {
        return repository.save(loanTypeToUpdate);
    }

    public LoanType findLoanTypeById(Long loanId) {
        return repository.findById(loanId)
                .orElseThrow(() -> new LoanTypeNotFoundException(loanId));
    }
}
