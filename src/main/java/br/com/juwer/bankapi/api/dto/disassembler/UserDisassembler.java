package br.com.juwer.bankapi.api.dto.disassembler;

import br.com.juwer.bankapi.api.dto.input.UserDTOInput;
import br.com.juwer.bankapi.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDisassembler {

    public User toDomainModel(UserDTOInput userDTOInput) {
        User user = new User();
        user.setFullName(userDTOInput.fullName());
        user.setEmail(userDTOInput.email());
        user.setPassword(userDTOInput.password());

        return user;
    }
}
