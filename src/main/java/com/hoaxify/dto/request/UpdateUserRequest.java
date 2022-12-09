package com.hoaxify.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UpdateUserRequest {

    @NotBlank
    @Size(min = 4, max = 50)
    private String displayName;
}
