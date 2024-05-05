package com.backend.app.models.dtos.auth;

import com.backend.app.persistence.entities.ERole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginGoogleUserDto (
        @NotBlank
        @Email(message = "Must be a valid email")
        String email,

        @NotBlank
        String imgUrl,

        @NotBlank
        Boolean isEmailVerified,

        @NotBlank
        Boolean isGoogleAccount,

        @NotBlank
        String firstName,

        @NotBlank
        String lastName

) {}
