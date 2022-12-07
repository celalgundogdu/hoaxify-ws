package com.hoaxify.user;

import com.hoaxify.dto.converter.UserDtoConverter;
import com.hoaxify.dto.request.CreateUserRequest;
import com.hoaxify.dto.response.UserResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoConverter converter;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserDtoConverter converter, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.converter = converter;
        this.passwordEncoder =  passwordEncoder;
    }

    public UserResponse createUser(CreateUserRequest createUserRequest) {
        User user = new User();
        user.setUsername(createUserRequest.getUsername());
        user.setDisplayName(createUserRequest.getDisplayName());
        user.setPassword(this.passwordEncoder.encode(createUserRequest.getPassword()));
        return converter.convertToUserResponse(userRepository.save(user));
    }
}
