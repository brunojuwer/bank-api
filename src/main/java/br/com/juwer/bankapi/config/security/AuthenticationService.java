package br.com.juwer.bankapi.config.security;

import br.com.juwer.bankapi.config.security.dto.AuthenticationResponse;
import br.com.juwer.bankapi.config.security.dtoinput.AuthenticationRequest;
import br.com.juwer.bankapi.config.security.dtoinput.RegisterRequest;
import br.com.juwer.bankapi.domain.model.Role;
import br.com.juwer.bankapi.domain.model.User;
import br.com.juwer.bankapi.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.firstname())
                .lastName(request.lastname())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
        String token = jwtService.generateToken(user);

        return new AuthenticationResponse(token, user.getEmail(), user.getId());
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        var user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        userRepository.save(user);
        String token = jwtService.generateToken(user);

        return new AuthenticationResponse(token, user.getEmail(), user.getId());
    }
}
