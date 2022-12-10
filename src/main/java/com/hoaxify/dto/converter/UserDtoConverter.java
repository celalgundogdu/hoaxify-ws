package com.hoaxify.dto.converter;

import com.hoaxify.dto.response.UserResponse;
import com.hoaxify.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {

    public UserResponse convertToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setDisplayName(user.getDisplayName());
        userResponse.setImage(user.getImage());
        return userResponse;
    }
}
