package com.backend.app.models.dtos.auth;

import com.backend.app.persistence.entities.ERole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterUserDto(
        @NotBlank
        @Email(message = "Must be a valid email")
        String email,

        @NotBlank
        String password,

        @NotBlank
        String phoneNumber,

        @NotBlank
        String dni,

        @NotBlank
        String firstName,

        @NotBlank
        String lastName

) {
}
