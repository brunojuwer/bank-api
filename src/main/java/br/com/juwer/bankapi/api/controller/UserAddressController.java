package br.com.juwer.bankapi.api.controller;

import br.com.juwer.bankapi.api.dto.assembler.AddressAssembler;
import br.com.juwer.bankapi.api.dto.disassembler.AddressDisassembler;
import br.com.juwer.bankapi.api.dto.input.AddressDTOInput;
import br.com.juwer.bankapi.api.dto.output.AddressDTO;
import br.com.juwer.bankapi.domain.model.Address;
import br.com.juwer.bankapi.domain.model.User;
import br.com.juwer.bankapi.domain.repository.AddressRepository;
import br.com.juwer.bankapi.domain.repository.UserRepository;
import br.com.juwer.bankapi.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users/{userId}/address")
@RequiredArgsConstructor
public class UserAddressController {

    private final AddressRepository addressRepository;
    private final AddressAssembler addressAssembler;
    private final AddressDisassembler addressDisassembler;
    private final UserService userService;

    @GetMapping
    public AddressDTO search(@PathVariable Long userId) {
        return addressAssembler.toModel(addressRepository.findAddressByUserId(userId));
    }

    @PostMapping
    public AddressDTO create(@RequestBody AddressDTOInput addressDTOInput, @PathVariable Long userId) {
        User user = userService.findUserById(userId);
        Address address = addressDisassembler.toDomainModel(addressDTOInput, user);
        return addressAssembler.toModel(addressRepository.save(address));
    }
}
