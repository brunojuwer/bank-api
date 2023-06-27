package br.com.juwer.bankapi.domain.service;

import br.com.juwer.bankapi.domain.model.Address;
import br.com.juwer.bankapi.domain.model.Customer;
import br.com.juwer.bankapi.domain.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    @Transactional
    public Address save(Address address, Customer customer) {
        Address savedAddress = addressRepository.save(address);
//        customer.setAddress(savedAddress);
        return savedAddress;
    }

    @Transactional
    public Address update(Address address, Long userId) {
        Address currentAddress = addressRepository.findAddressByUserId(userId);

        currentAddress.setStreet(address.getStreet());
        currentAddress.setCity(address.getCity());
        currentAddress.setState(address.getState());
        currentAddress.setPostalCode(address.getPostalCode());
        currentAddress.setCountry(address.getCountry());

        return addressRepository.save(currentAddress);
    }
}
