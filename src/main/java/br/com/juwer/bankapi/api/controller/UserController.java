package br.com.juwer.bankapi.api.controller;

import br.com.juwer.bankapi.api.dto.assembler.UserAssembler;
import br.com.juwer.bankapi.api.dto.output.UserDTO;
import br.com.juwer.bankapi.domain.model.User;
import br.com.juwer.bankapi.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserAssembler userAssembler;

    @GetMapping("/{userId}")
    public UserDTO test(@PathVariable Long userId) {
        User user = userService.findUserById(userId);
        return userAssembler.toModel(user);
    }
}
