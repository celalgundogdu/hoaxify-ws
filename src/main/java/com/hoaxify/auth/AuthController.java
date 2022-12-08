package com.hoaxify.auth;

import com.hoaxify.dto.converter.UserDtoConverter;
import com.hoaxify.dto.response.UserResponse;
import com.hoaxify.shared.CurrentUser;
import com.hoaxify.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserDtoConverter converter;

    public AuthController(UserDtoConverter converter) {
        this.converter = converter;
    }

    @PostMapping
    public ResponseEntity<UserResponse> handleAuthentication(@CurrentUser User user) {
        return ResponseEntity.ok(converter.convertToUserResponse(user));
    }
}
