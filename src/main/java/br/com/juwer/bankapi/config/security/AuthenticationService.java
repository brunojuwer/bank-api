package br.com.juwer.bankapi.config.security;

import br.com.juwer.bankapi.config.security.dto.AuthenticationResponse;
import br.com.juwer.bankapi.config.security.dtoinput.AuthenticationRequest;
import br.com.juwer.bankapi.domain.exceptions.AccountNotFoundException;
import br.com.juwer.bankapi.domain.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AccountRepository accountRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.code(), request.password())
        );

        var account = accountRepository.findByCode(request.code())
                .orElseThrow(() -> new AccountNotFoundException(request.code()));

//        accountRepository.save(account);
        String token = jwtService.generateToken(account);

        return new AuthenticationResponse(token, account.getCode());
    }
}
