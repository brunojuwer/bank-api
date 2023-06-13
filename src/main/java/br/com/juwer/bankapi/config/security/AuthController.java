package br.com.juwer.bankapi.config.security;

import br.com.juwer.bankapi.config.security.dto.AuthenticationResponse;
import br.com.juwer.bankapi.config.security.dtoinput.AuthenticationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @Deprecated
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register() {
        return ResponseEntity.status(HttpStatus.GONE).build();
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
