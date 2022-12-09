package com.hoaxify.user;

import com.hoaxify.dto.converter.UserDtoConverter;
import com.hoaxify.dto.request.CreateUserRequest;
import com.hoaxify.dto.response.UserResponse;
import com.hoaxify.error.ApiError;
import com.hoaxify.error.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Page<UserResponse> getUsers(Pageable page, User user) {
        if(user == null) {
            return userRepository.findAll(page).map(converter::convertToUserResponse);
        }
        return userRepository.findByUsernameNot(user.getUsername(), page).map(converter::convertToUserResponse);
    }

    public UserResponse getByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if(userOptional.isEmpty()){
            throw new NotFoundException("User not found: " + username);
        }
        return converter.convertToUserResponse(userOptional.get());
    }
}
