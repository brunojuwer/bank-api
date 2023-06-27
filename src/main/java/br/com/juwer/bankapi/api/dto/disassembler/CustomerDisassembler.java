package br.com.juwer.bankapi.api.dto.disassembler;

import br.com.juwer.bankapi.api.dto.input.CustomerDTOInput;
import br.com.juwer.bankapi.domain.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDisassembler {

    public Customer toDomainModel(CustomerDTOInput customerDTOInput) {
        Customer customer = new Customer();
        customer.setFullName(customerDTOInput.fullName());
        customer.setEmail(customerDTOInput.email());
        return customer;
    }
}
