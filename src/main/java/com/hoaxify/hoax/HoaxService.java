package com.hoaxify.hoax;

import com.hoaxify.dto.converter.HoaxDtoConverter;
import com.hoaxify.dto.request.CreateHoaxRequest;
import com.hoaxify.dto.response.HoaxResponse;
import com.hoaxify.user.User;
import com.hoaxify.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class HoaxService {

    private final UserService userService;
    private final HoaxRepository hoaxRepository;
    private final HoaxDtoConverter converter;

    public HoaxService(UserService userService, HoaxRepository hoaxRepository, HoaxDtoConverter converter) {
        this.userService = userService;
        this.hoaxRepository = hoaxRepository;
        this.converter = converter;
    }

    public HoaxResponse createHoax(CreateHoaxRequest createHoaxRequest, User user) {
        Hoax hoax = new Hoax();
        hoax.setContent(createHoaxRequest.getContent());
        hoax.setCreatedAt(new Date());
        hoax.setUser(user);
        return converter.convertToHoaxResponse(hoaxRepository.save(hoax));
    }

    public Page<HoaxResponse> getHoaxes(Pageable page) {
        return hoaxRepository.findAll(page).map(converter::convertToHoaxResponse);
    }

    public Page<HoaxResponse> getHoaxesByUsername(String username, Pageable page) {
        User user = userService.findByUsername(username);
        return hoaxRepository.findByUser(user, page).map(converter::convertToHoaxResponse);
    }
}
