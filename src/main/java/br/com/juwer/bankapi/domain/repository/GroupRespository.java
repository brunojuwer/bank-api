package br.com.juwer.bankapi.domain.repository;

import br.com.juwer.bankapi.domain.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupRespository extends JpaRepository<Group, Long> {
    @Query(value = "SELECT g.id, g.name FROM _group g " +
            "join _user_group ug on ug.group_id = g.id " +
            "where ug.user_id = :userId ", nativeQuery = true)
    List<Group> findAllGroupsByUserId(Long userId);
}
