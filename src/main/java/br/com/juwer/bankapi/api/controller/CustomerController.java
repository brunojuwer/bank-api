package br.com.juwer.bankapi.api.controller;

import br.com.juwer.bankapi.api.dto.assembler.CustomerAssembler;
import br.com.juwer.bankapi.api.dto.disassembler.CustomerDisassembler;
import br.com.juwer.bankapi.api.dto.input.CustomerInput;
import br.com.juwer.bankapi.api.dto.input.UserDTOPassword;
import br.com.juwer.bankapi.api.dto.output.UserDTO;
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
    private final CustomerAssembler customerAssembler;
    private final CustomerDisassembler customerDisassembler;
    private final CustomerRepository customerRepository;

    @GetMapping
    public List<UserDTO> findAll() {
        return customerAssembler.toColletionModel(customerRepository.findAll());
    }

    @GetMapping("/{userId}")
    public UserDTO find(@PathVariable Long userId) {
        Customer customer = customerService.findCustomerById(userId);
        return customerAssembler.toModel(customer);
    }

    @PostMapping
    public UserDTO create(@Valid @RequestBody CustomerInput customerDTOInput) {
        Customer customer = customerDisassembler.toDomainModel(customerDTOInput);
        return customerAssembler.toModel(customerService.save(customer));
    }

    @PutMapping("/{userId}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePassword(
        @PathVariable Long userId,
        @Valid @RequestBody UserDTOPassword userDTOPassword
    ){
        Customer customer = customerService.findCustomerById(userId);
        customerService.updatePassword(customer, userDTOPassword);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long userId) {
        customerService.delete(userId);
    }
}
