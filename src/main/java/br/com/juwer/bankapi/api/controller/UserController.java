package br.com.juwer.bankapi.api.controller;

import br.com.juwer.bankapi.api.dto.assembler.UserAssembler;
import br.com.juwer.bankapi.api.dto.disassembler.UserDisassembler;
import br.com.juwer.bankapi.api.dto.input.UserDTOInput;
import br.com.juwer.bankapi.api.dto.input.UserDTOPassword;
import br.com.juwer.bankapi.api.dto.output.UserDTO;
import br.com.juwer.bankapi.domain.model.User;
import br.com.juwer.bankapi.domain.repository.UserRepository;
import br.com.juwer.bankapi.domain.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserAssembler userAssembler;
    private final UserDisassembler userDisassembler;
    private final UserRepository userRepository;

    @GetMapping
    public List<UserDTO> findAll() {
        return userAssembler.toColletionModel(userRepository.findAll());
    }

    @GetMapping("/{userId}")
    public UserDTO find(@PathVariable Long userId) {
        User user = userService.findUserById(userId);
        return userAssembler.toModel(user);
    }

    @PostMapping
    public UserDTO create(@Valid @RequestBody UserDTOInput userDTOInput) {
        User user = userDisassembler.toDomainModel(userDTOInput);
        return userAssembler.toModel(userService.save(user));
    }

    @PutMapping("/{userId}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePassword(
        @PathVariable Long userId,
        @Valid @RequestBody UserDTOPassword userDTOPassword
    ){
        User user = userService.findUserById(userId);
        userService.updatePassword(user, userDTOPassword);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long userId) {
        userService.delete(userId);
    }
}
