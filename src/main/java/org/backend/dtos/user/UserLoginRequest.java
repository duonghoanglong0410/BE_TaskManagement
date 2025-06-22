package org.backend.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserLoginRequest {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    // ...getter, setter...
}