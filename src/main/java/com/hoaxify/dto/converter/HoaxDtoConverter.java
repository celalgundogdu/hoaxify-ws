package com.hoaxify.dto.converter;

import com.hoaxify.dto.response.HoaxResponse;
import com.hoaxify.hoax.Hoax;
import org.springframework.stereotype.Component;

@Component
public class HoaxDtoConverter {

    private final UserDtoConverter userDtoConverter;

    public HoaxDtoConverter(UserDtoConverter userDtoConverter) {
        this.userDtoConverter = userDtoConverter;
    }

    public HoaxResponse convertToHoaxResponse(Hoax hoax) {
        HoaxResponse hoaxResponse = new HoaxResponse();
        hoaxResponse.setId(hoax.getId());
        hoaxResponse.setContent(hoax.getContent());
        hoaxResponse.setCreatedAt(hoax.getCreatedAt().getTime());
        hoaxResponse.setUserResponse(userDtoConverter.convertToUserResponse(hoax.getUser()));
        return hoaxResponse;
    }
}
