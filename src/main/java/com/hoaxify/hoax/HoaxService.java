package com.hoaxify.hoax;

import com.hoaxify.dto.converter.HoaxDtoConverter;
import com.hoaxify.dto.request.CreateHoaxRequest;
import com.hoaxify.dto.response.HoaxResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class HoaxService {

    private final HoaxRepository hoaxRepository;
    private final HoaxDtoConverter converter;

    public HoaxService(HoaxRepository hoaxRepository, HoaxDtoConverter converter) {
        this.hoaxRepository = hoaxRepository;
        this.converter = converter;
    }

    public HoaxResponse createHoax(CreateHoaxRequest createHoaxRequest) {
        Hoax hoax = new Hoax();
        hoax.setContent(createHoaxRequest.getContent());
        hoax.setCreatedAt(new Date());
        return converter.convertToHoaxResponse(hoaxRepository.save(hoax));
    }

    public Page<HoaxResponse> getHoaxes(Pageable page) {
        return hoaxRepository.findAll(page).map(converter::convertToHoaxResponse);
    }
}
