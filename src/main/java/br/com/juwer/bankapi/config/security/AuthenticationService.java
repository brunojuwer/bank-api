package br.com.juwer.bankapi.config.security;

import br.com.juwer.bankapi.config.security.dto.AuthenticationResponse;
import br.com.juwer.bankapi.config.security.dtoinput.AuthenticationRequest;
import br.com.juwer.bankapi.domain.exceptions.AccountNotFoundException;
import br.com.juwer.bankapi.domain.exceptions.EntityNotFoundException;
import br.com.juwer.bankapi.domain.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        Authentication authenticate;

        try {
            authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.code(), request.password()));
        } catch(InternalAuthenticationServiceException e) {
            throw new AccountNotFoundException(request.code());
        }

        Account account = (Account) authenticate.getPrincipal();
        String token = jwtService.generateToken(account);

        return new AuthenticationResponse(token, account.getCode());
    }
}
