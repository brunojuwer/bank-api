package br.com.juwer.bankapi.config.security;

import br.com.juwer.bankapi.config.security.dto.AuthenticationResponse;
import br.com.juwer.bankapi.config.security.dtoinput.AuthenticationRequest;
import br.com.juwer.bankapi.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

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
