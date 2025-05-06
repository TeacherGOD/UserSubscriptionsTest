package com.example.usersubscriptionstest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotBlank(message = "Имя обязательно")
        String name,

        @NotBlank(message = "Email обязателен")
        @Email(message = "Некорректный email")
        String email,

        @Size(min = 8, max = 30, message = "Пароль должен быть от 8 до 30 символов")
        @NotBlank(message = "Пароль обязателен")
        String password
) {}