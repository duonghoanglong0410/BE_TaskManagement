package org.backend.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ForgotPasswordRequest {
    @Email
    @NotBlank
    private String email;
    // ...getter, setter...
}