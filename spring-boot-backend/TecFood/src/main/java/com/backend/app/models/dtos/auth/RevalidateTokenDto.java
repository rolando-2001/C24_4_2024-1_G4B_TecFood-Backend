package com.backend.app.models.dtos.auth;

import jakarta.validation.constraints.NotBlank;

public record RevalidateTokenDto(
        @NotBlank
        String refreshToken
) {
}
