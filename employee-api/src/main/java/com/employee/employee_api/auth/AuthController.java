package com.employee.employee_api.auth;

import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.employee.employee_api.model.User;
import com.employee.employee_api.repository.UserRepository;
import com.employee.employee_api.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository repo;
    private final JwtUtil jwt;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // constructor injection (no lombok)
    public AuthController(UserRepository repo, JwtUtil jwt) {
        this.repo = repo;
        this.jwt = jwt;
    }

    // REGISTER
    @PostMapping("/register")
    public String register(@RequestBody User user) {

        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);

        return "User Registered";
    }

    // LOGIN
    @PostMapping("/login")
    public String login(@RequestBody User user) {

        User dbUser = repo.findByUsername(user.getUsername()).orElse(null);

        if (dbUser != null &&
            encoder.matches(user.getPassword(), dbUser.getPassword())) {

            return jwt.generateToken(user.getUsername());
        }

        return "Invalid credentials";
    }
}
