package br.com.juwer.bankapi.domain.service;

import br.com.juwer.bankapi.api.dto.input.UserDTOPassword;
import br.com.juwer.bankapi.domain.exceptions.UserNotFoundException;
import br.com.juwer.bankapi.domain.model.Customer;
import br.com.juwer.bankapi.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder encoder;

    @Transactional
    public Customer save(Customer customer) {
//        customer.setPassword(encoder.encode(customer.getPassword()));
        return customerRepository.save(customer);
    }

    @Transactional
    public void updatePassword(Customer customer, UserDTOPassword userDTOPassword) {
//        final boolean currentPasswordMatches = encoder
//                .matches(userDTOPassword.currentPassword(), customer.getPassword());
//
//        if(!currentPasswordMatches) {
//            throw new CurrentPasswordDoesNotMatchException("Your current password does not match");
//        }
//        customer.setPassword(encoder.encode(userDTOPassword.newPassword()));
        customerRepository.save(customer);
    }

    @Transactional
    public void delete(Long userId) {
        this.findUserById(userId);
        this.customerRepository.deleteById(userId);
    }

    public Customer findUserById(Long userId) {
        return customerRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

}
