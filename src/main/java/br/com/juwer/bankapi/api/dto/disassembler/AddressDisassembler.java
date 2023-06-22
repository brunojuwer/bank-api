package br.com.juwer.bankapi.api.dto.disassembler;

import br.com.juwer.bankapi.api.dto.input.AddressDTOInput;
import br.com.juwer.bankapi.domain.model.Address;
import br.com.juwer.bankapi.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class AddressDisassembler {

    public Address toDomainModel(AddressDTOInput addressDTOInput) {
        return new Address(
                null,
                addressDTOInput.street(),
                addressDTOInput.city(),
                addressDTOInput.state(),
                addressDTOInput.postalCode(),
                addressDTOInput.country(),
                null
                );
    }
}
