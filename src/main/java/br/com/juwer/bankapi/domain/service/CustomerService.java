package br.com.juwer.bankapi.domain.service;

import br.com.juwer.bankapi.api.dto.input.UserDTOPassword;
import br.com.juwer.bankapi.domain.exceptions.ExistingCustomerException;
import br.com.juwer.bankapi.domain.exceptions.CustomerNotFoundException;
import br.com.juwer.bankapi.domain.model.Customer;
import br.com.juwer.bankapi.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    public Customer save(Customer customer) {
        this.verifyIfCustomerAlreadyExistsByEmailOrCpf(customer.getEmail(), customer.getCpf());
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
        this.findCustomerById(userId);
        this.customerRepository.deleteById(userId);
    }

    public Customer findCustomerById(Long userId) {
        return customerRepository.findById(userId)
                .orElseThrow(() -> new CustomerNotFoundException(userId));
    }

    private void verifyIfCustomerAlreadyExistsByEmailOrCpf(String email, String cpf) {
        customerRepository.findByEmail(email).ifPresent(existingCustomer -> {
            throw new ExistingCustomerException(existingCustomer.getEmail());
        });
        customerRepository.findByCpf(cpf).ifPresent(existingCustomer -> {
            throw new ExistingCustomerException(existingCustomer.getCpf());
        });
    }
}
