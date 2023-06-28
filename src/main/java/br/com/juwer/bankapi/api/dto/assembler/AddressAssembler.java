package br.com.juwer.bankapi.api.dto.assembler;

import br.com.juwer.bankapi.api.dto.output.AddressDTO;
import br.com.juwer.bankapi.domain.model.Address;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressAssembler {

    private final ModelMapper mapper;

    public AddressDTO toModel(Address address) {
        return mapper.map(address, AddressDTO.class);
    }
}
