package com.hoaxify.auth;

import com.hoaxify.shared.CurrentUser;
import com.hoaxify.user.User;
import com.hoaxify.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<?> handleAuthentication(@CurrentUser User user) {
        return ResponseEntity.ok(user);
    }
}
