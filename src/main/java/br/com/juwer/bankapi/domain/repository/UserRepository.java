package br.com.juwer.bankapi.domain.repository;

import br.com.juwer.bankapi.domain.model.Group;
import br.com.juwer.bankapi.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);


}
