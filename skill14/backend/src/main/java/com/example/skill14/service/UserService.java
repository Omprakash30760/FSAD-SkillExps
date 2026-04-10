package com.example.skill14.service;

import com.example.skill14.dto.LoginRequest;
import com.example.skill14.dto.RegisterRequest;
import com.example.skill14.model.User;
import com.example.skill14.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> register(RegisterRequest request) {
        if (request.getUsername() == null || request.getPassword() == null
                || request.getEmail() == null || request.getFullName() == null
                || request.getUsername().isBlank() || request.getPassword().isBlank()
                || request.getEmail().isBlank() || request.getFullName().isBlank()) {
            return Optional.empty();
        }

        if (userRepository.findByUsername(request.getUsername()).isPresent()
                || userRepository.findByEmail(request.getEmail()).isPresent()) {
            return Optional.empty();
        }

        User user = new User(
                request.getUsername().trim(),
                request.getEmail().trim(),
                request.getPassword(),
                request.getFullName().trim()
        );

        return Optional.of(userRepository.save(user));
    }

    public Optional<User> login(LoginRequest request) {
        if (request.getUsername() == null || request.getPassword() == null) {
            return Optional.empty();
        }

        return userRepository.findByUsername(request.getUsername().trim())
                .filter(user -> user.getPassword().equals(request.getPassword()));
    }

    public Optional<User> getProfile(Long userId, String username) {
        if (userId != null) {
            return userRepository.findById(userId);
        }
        if (username != null && !username.isBlank()) {
            return userRepository.findByUsername(username.trim());
        }
        return Optional.empty();
    }
}
