package com.hoaxify.dto.request;

import com.hoaxify.user.UniqueUsername;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class CreateUserRequest {

    @NotBlank(message = "{hoaxify.constraint.username.NotBlank.message}")
    @Size(min = 4, max = 50)
    @UniqueUsername
    private String username;

    @NotBlank
    @Size(min = 4, max = 50)
    private String displayName;

    @NotNull
    @Size(min = 8, max = 255)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$",
            message = "{hoaxify.constraint.password.Pattern.message}")
    private String password;
}
