package com.example.silenokkombat.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistrationDto {
    @NotBlank(message = "Не указано поле username")
    @Size(min = 3, max = 30, message = "Логин не может быть меньше 3 или больше 30")
    private String username;
    @NotBlank(message = "Не указано поле password")
    @Size(min = 8, max = 30, message = "Пароль не может быть меньше 8 или больше 30")
    private String password;
    @NotBlank(message = "Не указано поле confirmPassword")
    @Size(min = 8, max = 30, message = "Подтверждение пароля не может быть меньше 8 или больше 30")
    private String confirmPassword;
    @NotBlank(message = "Не указано поле email")
    @Email(message = "Почта должна быть валидной")
    private String email;
    @NotNull(message = "Не указано поле code")
    @Size(max = 6, message = "Код не может быть больше 6")
    private String code;
}
