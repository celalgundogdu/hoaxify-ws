package com.hoaxify.dto.converter;

import com.hoaxify.dto.response.HoaxResponse;
import com.hoaxify.hoax.Hoax;
import org.springframework.stereotype.Component;

@Component
public class HoaxDtoConverter {

    public HoaxResponse convertToHoaxResponse(Hoax hoax) {
        HoaxResponse hoaxResponse = new HoaxResponse();
        hoaxResponse.setId(hoax.getId());
        hoaxResponse.setContent(hoax.getContent());
        return hoaxResponse;
    }
}
