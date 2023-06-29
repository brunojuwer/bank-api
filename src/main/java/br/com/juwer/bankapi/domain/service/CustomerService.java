package br.com.juwer.bankapi.domain.service;

import br.com.juwer.bankapi.api.dto.input.AccountInputPassword;
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
        this.verifyIfCustomerAlreadyExistsByEmail(customer.getEmail());
        this.verifyIfCustomerAlreadyExistsByCpf(customer.getCpf());

        return customerRepository.save(customer);
    }

    @Transactional
    public Customer update(Customer customer, Customer customerWithOldData) {
        customer.setId(customerWithOldData.getId());
        customer.setCreatedAt(customerWithOldData.getCreatedAt());

        boolean isEmailNew = !customer.getEmail().equals(customerWithOldData.getEmail());
        boolean isCpfNew = !customer.getCpf().equals(customerWithOldData.getCpf());

        if(isEmailNew){
            verifyIfCustomerAlreadyExistsByEmail(customer.getEmail());
        }
        if (isCpfNew) {
            verifyIfCustomerAlreadyExistsByCpf(customer.getCpf());
        }
        return customerRepository.save(customer);
    }

    @Transactional
    public void delete(Long userId) {
        this.findCustomerById(userId);
        this.customerRepository.deleteById(userId);
    }

    public Customer findCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    private void verifyIfCustomerAlreadyExistsByEmail(String email) {
        customerRepository.findByEmail(email).ifPresent(existingCustomer -> {
            throw new ExistingCustomerException(existingCustomer.getEmail());
        });
    }

    private void verifyIfCustomerAlreadyExistsByCpf(String cpf) {
        customerRepository.findByCpf(cpf).ifPresent(existingCustomer -> {
            throw new ExistingCustomerException(existingCustomer.getCpf());
        });
    }
}
