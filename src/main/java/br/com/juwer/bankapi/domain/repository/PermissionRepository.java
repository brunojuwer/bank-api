package br.com.juwer.bankapi.domain.repository;

import br.com.juwer.bankapi.domain.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
