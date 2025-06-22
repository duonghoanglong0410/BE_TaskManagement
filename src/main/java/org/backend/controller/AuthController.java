package org.backend.controller;

import org.backend.dtos.auth.LoginRequest;
import org.backend.dtos.auth.RegisterRequest;
import org.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            String jwt = authService.login(loginRequest);
            return ResponseEntity.ok(Collections.singletonMap("token", jwt));
        } catch (Exception ex) {
            System.out.println("Login failed for email: " + loginRequest.getEmail());
            return ResponseEntity.status(401).body("Sai tài khoản hoặc mật khẩu!");
        }
    }




    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }
}