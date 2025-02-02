package com.dev.ServiceHelp.models.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewPasswordDTO {

        private String token;
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$",
                message = "A senha deve conter pelo menos uma letra maiúscula e um caractere especial, e ter no mínimo 8 caracteres")
        private String password;
}