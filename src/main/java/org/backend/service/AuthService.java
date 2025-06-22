package org.backend.service;

import org.backend.dtos.auth.JwtResponse;
import org.backend.dtos.auth.LoginRequest;
import org.backend.dtos.auth.RegisterRequest;

public interface AuthService {
    String login(LoginRequest request);
    String register(RegisterRequest request);
}
