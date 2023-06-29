package br.com.juwer.bankapi.api.dto.assembler;

import br.com.juwer.bankapi.api.dto.output.ResumeCustomerDTO;
import br.com.juwer.bankapi.domain.model.Customer;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ResumeCustomerAssembler {

    private final ModelMapper mapper;

    public ResumeCustomerDTO toModel(Customer customer) {
        return mapper.map(customer, ResumeCustomerDTO.class);
    }

    public List<ResumeCustomerDTO> toCollectionModel(List<Customer> customers) {
        return customers.stream()
                .map(this::toModel)
                .toList();
    }
}
