package com.hoaxify.dto.response;

import lombok.Data;

@Data
public class HoaxResponse {

    private Long id;
    private String content;
    private long createdAt;
    private UserResponse userResponse;
}
