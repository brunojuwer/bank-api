package br.com.juwer.bankapi.domain.service;

import br.com.juwer.bankapi.domain.exceptions.UserNotFoundException;
import br.com.juwer.bankapi.domain.model.User;
import br.com.juwer.bankapi.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

}
