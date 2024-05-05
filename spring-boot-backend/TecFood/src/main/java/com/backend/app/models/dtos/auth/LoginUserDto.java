package com.backend.app.models.dtos.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginUserDto(
        @NotBlank
        @Email(message = "Must be a valid email")
        String email,

        @NotBlank
        String password

) { }