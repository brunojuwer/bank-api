package br.com.juwer.bankapi.api.dto.assembler;

import br.com.juwer.bankapi.api.dto.output.AddressDTO;
import br.com.juwer.bankapi.domain.model.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressAssembler {

    public AddressDTO toModel(Address address) {
        return new AddressDTO(
                address.getId(),
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getPostalCode(),
                address.getCountry()
        );
    }
}
