package br.com.juwer.bankapi.unit.service;

import br.com.juwer.bankapi.api.dto.input.UserDTOPassword;
import br.com.juwer.bankapi.domain.model.Customer;
import br.com.juwer.bankapi.domain.repository.CustomerRepository;
import br.com.juwer.bankapi.domain.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    PasswordEncoder encoder;

    @Captor
    ArgumentCaptor<Customer> userCaptor;

    @Test
    void itShouldSaveAUserWithSuccess() {
        Customer customer = generateSimpleUser();

        when(customerRepository.save(any())).thenReturn(generateSimpleUserWithId());
        Customer createdCustomer = userService.save(customer);

        verify(customerRepository).save(customer);
        assertEquals(1L, createdCustomer.getId());
    }

    @Test
    void itShouldUpdateUserPasswordWithSuccess() {
//        Customer customer = generateSimpleUserWithIdAndEncodedPassword();
//        UserDTOPassword userDTOPassword = generateUserDTOPassword();
//
//        when(encoder.matches(userDTOPassword.currentPassword(), customer.getPassword())).thenReturn(true);
//        userService.updatePassword(customer, userDTOPassword);
//
//        verify(customerRepository).save(userCaptor.capture());
    }
    @Test
    void itShouldThrowCurrentPasswordDoesNotMatchException() {
//
//        Customer customer = generateSimpleUserWithIdAndEncodedPassword();
//        UserDTOPassword userDTOPassword = generateUserDTOPassword();
//
//        when(encoder.matches(userDTOPassword.currentPassword(), customer.getPassword())).thenReturn(false);
//        Throwable exception = Assertions.catchThrowable(() -> userService.updatePassword(customer, userDTOPassword));
//
//        assertEquals("Your current password does not match", exception.getMessage());
//        verify(customerRepository, Mockito.never()).save(customer);
    }

    @Test
    void itShouldDeleteUserWithSuccess() {
        Long userId = 1L;
        Customer customer = generateSimpleUserWithId();

        when(customerRepository.findById(userId)).thenReturn(Optional.of(customer));
        userService.delete(userId);

        verify(customerRepository).findById(userId);
        verify(customerRepository).deleteById(userId);
    }

    @Test
    void itShouldThrowUserNotFoundExceptionWhenDeleteAUserWithNonexistentId() {
        Long userId = 1L;

        when(customerRepository.findById(userId)).thenReturn(Optional.empty());
        Throwable exception = Assertions.catchThrowable(() -> userService.delete(userId));

        assertEquals("User with id 1 not found", exception.getMessage());
        verify(customerRepository, never()).deleteById(userId);
    }

    @Test
    void itShouldThrowUserNotFoundException() {
        Long userId = 1L;

        when(customerRepository.findById(userId)).thenReturn(Optional.empty());
        Throwable exception = Assertions.catchThrowable(() -> userService.findUserById(userId));

        assertEquals("User with id 1 not found", exception.getMessage());
    }

    private static Customer generateSimpleUser() {
        return Customer.builder()
                .email("email@email.com")
//                .password("1234")
                .fullName("Fulano da Silva")
                .build();
    }

    private static Customer generateSimpleUserWithId() {
        return Customer.builder()
                .id(1L)
                .email("email@email.com")
//                .password("1234")
                .fullName("Fulano da Silva")
                .build();
    }

    private static Customer generateSimpleUserWithIdAndEncodedPassword() {
        return Customer.builder()
                .id(1L)
                .email("email@email.com")
//                .password("$2a$10$BNkOUm6tdcy.I8Vu5ll5VOx7y9k.uI9FcED3jlyBdWCYsi7Zjlsji")
                .fullName("Fulano da Silva")
                .build();
    }

    private static UserDTOPassword generateUserDTOPassword() {
        return new UserDTOPassword("qwe@123", "4321");
    }
}