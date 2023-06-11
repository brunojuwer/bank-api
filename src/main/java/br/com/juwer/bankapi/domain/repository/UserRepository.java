package br.com.juwer.bankapi.domain.repository;

import br.com.juwer.bankapi.domain.model.Address;
import br.com.juwer.bankapi.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Address findUserAddress(Long userId);
}
