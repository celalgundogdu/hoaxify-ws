package com.hoaxify.user;

import com.hoaxify.dto.request.CreateUserRequest;
import com.hoaxify.dto.request.UpdateUserRequest;
import com.hoaxify.dto.response.UserResponse;
import com.hoaxify.shared.CurrentUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        return new ResponseEntity<>(userService.createUser(createUserRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<UserResponse>> getUsers(Pageable page, @CurrentUser User user) {
        return ResponseEntity.ok(userService.getUsers(page, user));
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String username) {
        return ResponseEntity.ok(userService.getByUsername(username));
    }

    @PutMapping("/{username}")
    @PreAuthorize("#username == principal.username")
    public ResponseEntity<UserResponse> update(@Valid @RequestBody UpdateUserRequest updateUserRequest,
                                               @PathVariable String username) {
        return ResponseEntity.ok(userService.updateUser(username, updateUserRequest));
    }
}
