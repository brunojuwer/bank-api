package br.com.juwer.bankapi.domain.repository;

import br.com.juwer.bankapi.domain.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRespository extends JpaRepository<Group, Long> {}
