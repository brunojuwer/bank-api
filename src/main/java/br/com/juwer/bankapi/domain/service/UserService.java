package br.com.juwer.bankapi.domain.service;

import br.com.juwer.bankapi.api.dto.input.UserDTOPassword;
import br.com.juwer.bankapi.domain.exceptions.CurrentPasswordDoesNotMatchException;
import br.com.juwer.bankapi.domain.exceptions.UserNotFoundException;
import br.com.juwer.bankapi.domain.model.User;
import br.com.juwer.bankapi.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Transactional
    public User save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Transactional
    public void updatePassword(User user, UserDTOPassword userDTOPassword) {
        final boolean currentPasswordMatches = encoder
                .matches(userDTOPassword.currentPassword(), user.getPassword());

        if(!currentPasswordMatches) {
            throw new CurrentPasswordDoesNotMatchException("Your current password does not match");
        }
        user.setPassword(encoder.encode(userDTOPassword.newPassword()));
        userRepository.save(user);
    }

    @Transactional
    public void delete(Long userId) {
        this.findUserById(userId);
        this.userRepository.deleteById(userId);
    }

    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

}
