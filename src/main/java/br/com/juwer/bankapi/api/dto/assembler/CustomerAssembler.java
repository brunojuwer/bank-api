package br.com.juwer.bankapi.api.dto.assembler;

import br.com.juwer.bankapi.api.dto.output.UserDTO;
import br.com.juwer.bankapi.domain.model.Customer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerAssembler {

    public UserDTO toModel(Customer customer) {
        return new UserDTO(
                customer.getId(),
                customer.getFullName(),
                customer.getEmail()
        );
    }

    public List<UserDTO> toColletionModel(List<Customer> customers) {
        return customers.stream()
                .map(this::toModel)
                .toList();
    }
}
