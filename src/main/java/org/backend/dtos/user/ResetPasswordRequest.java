package org.backend.dtos.user;

import jakarta.validation.constraints.NotBlank;

public class ResetPasswordRequest {
    @NotBlank
    private String token;
    @NotBlank
    private String newPassword;
    // ...getter, setter...
}