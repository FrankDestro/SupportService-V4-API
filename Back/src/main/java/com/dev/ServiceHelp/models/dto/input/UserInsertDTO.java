package com.dev.ServiceHelp.models.dto.input;

import com.dev.ServiceHelp.models.dto.shared.UserDTO;
import com.dev.ServiceHelp.services.validation.UserInsertValid;
import jakarta.validation.constraints.Pattern;

@UserInsertValid
public class UserInsertDTO {
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$",
            message = "A senha deve conter pelo menos uma letra maiúscula e um caractere especial, e ter no mínimo 8 caracteres")
    String password;
    UserDTO userDetails;
}
