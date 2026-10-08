package com.artgallery.artgallery.controller;

import com.artgallery.artgallery.model.User;
import com.artgallery.artgallery.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {
        "http://127.0.0.1:5500",
        "http://localhost:5500"
})
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {

        userRepository.save(user);

        return ResponseEntity.ok(
                "Registration successful for " + user.getName()
        );
    }

    @PostMapping("/login")
public ResponseEntity<String> login(@RequestBody User user) {

    Optional<User> existingUser =
            userRepository.findByEmail(user.getEmail());

    if (existingUser.isPresent() &&
            existingUser.get().getPassword().equals(user.getPassword())) {

        return ResponseEntity.ok("Login successful");
    }

    return ResponseEntity
            .status(401)
            .body("Invalid email or password");
}
}