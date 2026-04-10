package com.example.skill14.controller;

import com.example.skill14.dto.UserProfileResponse;
import com.example.skill14.model.User;
import com.example.skill14.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String username
    ) {
        Optional<User> user = userService.getProfile(userId, username);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User found = user.get();
        return ResponseEntity.ok(new UserProfileResponse(
                found.getId(),
                found.getUsername(),
                found.getEmail(),
                found.getFullName()
        ));
    }
}
