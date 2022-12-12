package com.hoaxify.hoax;

import com.hoaxify.dto.request.CreateHoaxRequest;
import com.hoaxify.dto.response.HoaxResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/hoaxes")
public class HoaxController {

    private final HoaxService hoaxService;

    public HoaxController(HoaxService hoaxService) {
        this.hoaxService = hoaxService;
    }

    @PostMapping
    public ResponseEntity<HoaxResponse> createHoax(@Valid @RequestBody CreateHoaxRequest createHoaxRequest) {
        return new ResponseEntity(hoaxService.createHoax(createHoaxRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<HoaxResponse>> getHoaxes(@PageableDefault(sort = "id",
                                                                         direction = Sort.Direction.DESC) Pageable page) {
        return ResponseEntity.ok(hoaxService.getHoaxes(page));
    }
}
