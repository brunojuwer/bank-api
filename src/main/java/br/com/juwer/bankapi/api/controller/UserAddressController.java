package br.com.juwer.bankapi.api.controller;

import br.com.juwer.bankapi.api.dto.assembler.AddressAssembler;
import br.com.juwer.bankapi.api.dto.output.AddressDTO;
import br.com.juwer.bankapi.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users/{userId}/address")
@RequiredArgsConstructor
public class UserAddressController {

    private final UserRepository userRepository;
    private final AddressAssembler addressAssembler;

    @GetMapping
    public AddressDTO search(@PathVariable Long userId) {
        return addressAssembler.toModel(userRepository.findUserAddress(userId));
    }
}
