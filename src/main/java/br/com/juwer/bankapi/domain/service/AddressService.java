package br.com.juwer.bankapi.domain.service;

import br.com.juwer.bankapi.domain.model.Address;
import br.com.juwer.bankapi.domain.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    @Transactional
    public Address save(Address address) {
        address.getUser().setAddress(address);
        return addressRepository.save(address);
    }
}
