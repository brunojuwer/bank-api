package br.com.juwer.bankapi.unit.service;

import br.com.juwer.bankapi.api.dto.input.UserDTOPassword;
import br.com.juwer.bankapi.domain.model.User;
import br.com.juwer.bankapi.domain.repository.UserRepository;
import br.com.juwer.bankapi.domain.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder encoder;

    @Captor
    ArgumentCaptor<User> userCaptor;

    @Test
    void itShouldSaveAUserWithSuccess() {
        User user = generateSimpleUser();

        when(userRepository.save(any())).thenReturn(generateSimpleUserWithId());
        User createdUser = userService.save(user);

        verify(userRepository).save(user);
        assertEquals(1L, createdUser.getId());
    }

    @Test
    void itShouldUpdateUserPasswordWithSuccess() {
        User user = generateSimpleUserWithIdAndEncodedPassword();
        UserDTOPassword userDTOPassword = generateUserDTOPassword();

        when(encoder.matches(userDTOPassword.currentPassword(), user.getPassword())).thenReturn(true);
        userService.updatePassword(user, userDTOPassword);

        verify(userRepository).save(userCaptor.capture());
    }
    @Test
    void itShouldThrowCurrentPasswordDoesNotMatchException() {

        User user = generateSimpleUserWithIdAndEncodedPassword();
        UserDTOPassword userDTOPassword = generateUserDTOPassword();

        when(encoder.matches(userDTOPassword.currentPassword(), user.getPassword())).thenReturn(false);
        Throwable exception = Assertions.catchThrowable(() -> userService.updatePassword(user, userDTOPassword));

        assertEquals("Your current password does not match", exception.getMessage());
        verify(userRepository, Mockito.never()).save(user);
    }

    @Test
    void itShouldDeleteUserWithSuccess() {
        Long userId = 1L;
        User user = generateSimpleUserWithId();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        userService.delete(userId);

        verify(userRepository).findById(userId);
        verify(userRepository).deleteById(userId);
    }

    @Test
    void itShouldThrowUserNotFoundExceptionWhenDeleteAUserWithNonexistentId() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        Throwable exception = Assertions.catchThrowable(() -> userService.delete(userId));

        assertEquals("User with id 1 not found", exception.getMessage());
        verify(userRepository, never()).deleteById(userId);
    }

    @Test
    void itShouldThrowUserNotFoundException() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        Throwable exception = Assertions.catchThrowable(() -> userService.findUserById(userId));

        assertEquals("User with id 1 not found", exception.getMessage());
    }

    private static User generateSimpleUser() {
        return User.builder()
                .email("email@email.com")
                .password("1234")
                .fullName("Fulano da Silva")
                .build();
    }

    private static User generateSimpleUserWithId() {
        return User.builder()
                .id(1L)
                .email("email@email.com")
                .password("1234")
                .fullName("Fulano da Silva")
                .build();
    }

    private static User generateSimpleUserWithIdAndEncodedPassword() {
        return User.builder()
                .id(1L)
                .email("email@email.com")
                .password("$2a$10$BNkOUm6tdcy.I8Vu5ll5VOx7y9k.uI9FcED3jlyBdWCYsi7Zjlsji")
                .fullName("Fulano da Silva")
                .build();
    }

    private static UserDTOPassword generateUserDTOPassword() {
        return new UserDTOPassword("qwe@123", "4321");
    }
}