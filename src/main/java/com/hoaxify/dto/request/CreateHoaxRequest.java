package com.hoaxify.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CreateHoaxRequest {

    @NotNull
    @Size(min = 1, max = 1000)
    private String content;
}
