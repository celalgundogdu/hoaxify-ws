package com.hoaxify.dto.request;

import com.hoaxify.shared.FileType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UpdateUserRequest {

    @Size(min = 4, max = 50)
    private String displayName;

    @FileType(types = {"jpeg", "png"})
    private String image;

    public void setDisplayName(String displayName) {
        this.displayName = displayName.trim();
    }
}
