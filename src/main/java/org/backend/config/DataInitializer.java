package org.backend.config;
import org.backend.models.User;
import org.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        String email = "admin@example.com";
        if (!userRepository.existsByEmail(email)) {
            User user = new User();
            user.setEmail(email);
            user.setName("Admin");
            user.setPassword(passwordEncoder.encode("admin123"));
            user.setEnabled(true);
            userRepository.save(user);
            System.out.println("Default admin account created: " + email + " / admin123");
        }
    }
}