package br.com.juwer.bankapi.api.controller;

import br.com.juwer.bankapi.api.dto.output.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @GetMapping("/{userId}")
    public UserDTO test(@PathVariable Long userId) {
        return null;
    }
}
