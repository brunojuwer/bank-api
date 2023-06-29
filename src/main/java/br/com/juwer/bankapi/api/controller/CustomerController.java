package br.com.juwer.bankapi.api.controller;

import br.com.juwer.bankapi.api.dto.assembler.CustomerAssembler;
import br.com.juwer.bankapi.api.dto.assembler.ResumeCustomerAssembler;
import br.com.juwer.bankapi.api.dto.disassembler.CustomerDisassembler;
import br.com.juwer.bankapi.api.dto.input.CustomerInput;
import br.com.juwer.bankapi.api.dto.output.CustomerDTO;
import br.com.juwer.bankapi.api.dto.output.ResumeCustomerDTO;
import br.com.juwer.bankapi.config.security.verification.CheckSecurity;
import br.com.juwer.bankapi.domain.model.Customer;
import br.com.juwer.bankapi.domain.repository.CustomerRepository;
import br.com.juwer.bankapi.domain.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final ResumeCustomerAssembler resumeCustomerAssembler;
    private final CustomerAssembler customerAssembler;
    private final CustomerDisassembler customerDisassembler;
    private final CustomerRepository customerRepository;

    @GetMapping
    public List<CustomerDTO> findAll() {
        return customerAssembler.toCollectionModel(customerRepository.findAll());
    }

    @GetMapping(params = "projection=resume")
    public List<ResumeCustomerDTO> findAllResume() {
        return resumeCustomerAssembler.toCollectionModel(customerRepository.findAll());
    }

    @GetMapping("/{customerId}")
    @CheckSecurity.Customers.CanConsult
    public CustomerDTO findCustomer(@PathVariable Long customerId) {
        Customer customer = customerService.findCustomerById(customerId);
        return customerAssembler.toModel(customer);
    }

    @GetMapping(path = "/{customerId}", params = "projection=resume")
    @CheckSecurity.Customers.CanConsult
    public ResumeCustomerDTO findResumeCustomer(@PathVariable Long customerId) {
        Customer customer = customerService.findCustomerById(customerId);
        return resumeCustomerAssembler.toModel(customer);
    }


    @PostMapping
    @CheckSecurity.Customers.CanEdit
    public ResumeCustomerDTO create(@Valid @RequestBody CustomerInput customerInput) {
        Customer customer = customerDisassembler.toDomainModel(customerInput);
        return resumeCustomerAssembler.toModel(customerService.save(customer));
    }

    @PutMapping("/{customerId}")
    @CheckSecurity.Customers.CanUpdate
    public CustomerDTO update(
        @Valid @RequestBody CustomerInput customerInput,
        @PathVariable Long customerId
    ) {
        Customer savedCustomer = customerService.findCustomerById(customerId);
        Customer customerWithNewData = customerDisassembler.toDomainModel(customerInput);

        return customerAssembler.toModel(customerService.update(customerWithNewData, savedCustomer));
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CheckSecurity.Customers.CanEdit
    public void delete(@PathVariable Long customerId) {
        customerService.delete(customerId);
    }
}
