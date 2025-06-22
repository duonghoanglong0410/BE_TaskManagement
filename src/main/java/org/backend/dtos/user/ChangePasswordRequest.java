package org.backend.dtos.user;

import jakarta.validation.constraints.NotBlank;

public class ChangePasswordRequest {
    @NotBlank
    private String oldPassword;
    @NotBlank
    private String newPassword;
    // ...getter, setter...
}