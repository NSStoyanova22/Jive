package com.jive.service;

import com.jive.model.User;
import com.jive.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HexFormat;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> allUsers() {
        return userRepo.findAll();
    }

    public User getUser(long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + id));
    }

    public User register(String username, String email, String passwordPlain) {
        if (userRepo.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username already taken.");
        }
        if (userRepo.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email already used.");
        }

        // For now: simple SHA-256 hash so you don't store plain passwords.
        // Later you’ll replace with BCrypt via Spring Security.
        String hash = sha256(passwordPlain);

        return userRepo.save(new User(username, email, hash));
    }

    private String sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] out = md.digest(input.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(out);
        } catch (Exception e) {
            throw new RuntimeException("Hashing failed", e);
        }
    }
}