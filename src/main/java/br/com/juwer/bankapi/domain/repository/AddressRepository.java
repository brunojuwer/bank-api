package br.com.juwer.bankapi.domain.repository;

import br.com.juwer.bankapi.domain.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findAddressByUserId(Long userId);
}
