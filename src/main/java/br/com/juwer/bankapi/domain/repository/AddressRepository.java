package br.com.juwer.bankapi.domain.repository;

import br.com.juwer.bankapi.domain.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("from Address a join fetch a.user where a.user.id = :userId")
    Address findAddressByUserId(Long userId);
}
