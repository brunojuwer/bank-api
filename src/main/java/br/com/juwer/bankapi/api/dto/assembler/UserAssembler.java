package br.com.juwer.bankapi.api.dto.assembler;

import br.com.juwer.bankapi.api.dto.output.UserDTO;
import br.com.juwer.bankapi.domain.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserAssembler {

    public UserDTO toModel(User user) {
        return new UserDTO(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getGroups()
        );
    }

    public List<UserDTO> toColletionModel(List<User> users) {
        return users.stream()
                .map(this::toModel)
                .toList();
    }
}
