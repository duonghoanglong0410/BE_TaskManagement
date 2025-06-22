package org.backend.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRegisterRequest {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Size(min = 6)
    private String password;
    @NotBlank
    private String name;
    private String avatarUrl;
    private String phoneNumber;
    // ...getter, setter...
}