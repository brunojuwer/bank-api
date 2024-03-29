package br.com.juwer.bankapi.unit.service;

import br.com.juwer.bankapi.domain.model.Address;
import br.com.juwer.bankapi.domain.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @InjectMocks
    AddressService addressService;

    @Mock
    AddressRepository addressRepository;

    @Test
    void itShouldSaveAnAddressWithSuccess() {
        Address address = createNewAddress();
        Address addressWithId = createNewAddressWithId();
        Customer customer = generateSimpleUserWithId();

        when(addressRepository.save(any())).thenReturn(addressWithId);
        Address createdAddress = addressService.save(address, customer);

        verify(addressRepository).save(address);
        assertNotNull(createdAddress.getId());
        assertEquals(customer.getAddress(), createdAddress);
    }



    @Test
    void itShouldUpdateAnAddressWithSuccess() {
//        Address addresWithNewData = createNewAddressWithNewData();
//
//        when(addressRepository.findAddressByUserId(1L)).thenReturn(createNewAddressWithId());
//        when(addressRepository.save(addresWithNewDataAndId())).thenReturn(addresWithNewDataAndId());
//
//        Address updatedAddress = addressService.update(addresWithNewData, 1L);
//
//        verify(addressRepository).save(addresWithNewDataAndId());
//        verify(addressRepository).findAddressByUserId(1L);
//        assertEquals(updatedAddress, addresWithNewDataAndId());
    }

    private static Address createNewAddress() {
        return Address.builder()
                .street("Rua Tiradentes")
                .city("Porto Alegre")
                .state("Rio Grande do Sul")
                .postalCode("90000000")
                .country("Brasil")
                .build();
    }

    private static Address createNewAddressWithId() {
        return Address.builder()
                .id(1L)
                .street("Rua Tiradentes")
                .city("Porto Alegre")
                .state("Rio Grande do Sul")
                .postalCode("90000000")
                .country("Brasil")
                .build();
    }

    private static Address createNewAddressWithNewData() {
        return Address.builder()
                .street("Rua Alvorada")
                .city("Porto Alegre")
                .state("Rio Grande do Sul")
                .postalCode("90000000")
                .country("Brasil")
                .build();
    }

    private static Address addresWithNewDataAndId() {
        return Address.builder()
                .id(1L)
                .street("Rua Alvorada")
                .city("Porto Alegre")
                .state("Rio Grande do Sul")
                .postalCode("90000000")
                .country("Brasil")
                .build();
    }

    private static Customer generateSimpleUserWithId() {
        return Customer.builder()
                .id(1L)
                .email("email@email.com")
                .fullName("Fulano da Silva")
                .build();
    }
}