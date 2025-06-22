package org.backend.ServiceImplementors;

import org.backend.dtos.auth.LoginRequest;
import org.backend.dtos.auth.JwtResponse;
import org.backend.dtos.auth.RegisterRequest;
import org.backend.models.User;
import org.backend.repository.UserRepository;
import org.backend.service.AuthService;
import org.backend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String login(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(), loginRequest.getPassword())
            );
            String jwt = jwtUtil.generateToken(loginRequest.getEmail());
            return jwt; // Trả về chuỗi JWT
        } catch (Exception ex) {
            ex.printStackTrace();  // In log chi tiết
            throw ex; // hoặc trả về response custom
        }
    }



    @Override
    public String register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return "Email already registered!";
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        user.setEnabled(true);
        user.setLocked(false);

        userRepository.save(user);
        return "Register successfully!";
    }
}