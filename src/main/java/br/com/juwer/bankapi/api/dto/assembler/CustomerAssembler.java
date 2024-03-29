package br.com.juwer.bankapi.api.dto.assembler;

import br.com.juwer.bankapi.api.dto.output.CustomerDTO;
import br.com.juwer.bankapi.domain.model.Customer;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomerAssembler {

    private final ModelMapper mapper;

    public CustomerDTO toModel(Customer customer) {
        return mapper.map(customer, CustomerDTO.class);
    }

    public List<CustomerDTO> toCollectionModel(List<Customer> customers) {
        return customers.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
