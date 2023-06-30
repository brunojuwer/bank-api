package br.com.juwer.bankapi.api.dto.input;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountInputPassword {

    @NotBlank
    private String currentPassword;

    @NotBlank
    private String newPassword;
}
