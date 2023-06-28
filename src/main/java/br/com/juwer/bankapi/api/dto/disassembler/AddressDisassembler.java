package br.com.juwer.bankapi.api.dto.disassembler;

import br.com.juwer.bankapi.api.dto.input.AddressDTOInput;
import br.com.juwer.bankapi.domain.model.Address;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressDisassembler {

    private final ModelMapper mapper;

    public Address toDomainModel(AddressDTOInput addressDTOInput) {
        return mapper.map(addressDTOInput, Address.class);
    }
}
