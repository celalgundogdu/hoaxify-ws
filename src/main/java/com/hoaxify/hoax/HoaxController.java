package com.hoaxify.hoax;

import com.hoaxify.dto.request.CreateHoaxRequest;
import com.hoaxify.dto.response.HoaxResponse;
import com.hoaxify.shared.CurrentUser;
import com.hoaxify.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class HoaxController {

    private final HoaxService hoaxService;

    public HoaxController(HoaxService hoaxService) {
        this.hoaxService = hoaxService;
    }

    @PostMapping("/hoaxes")
    public ResponseEntity<HoaxResponse> createHoax(@Valid @RequestBody CreateHoaxRequest createHoaxRequest,
                                                   @CurrentUser User user) {
        return new ResponseEntity(hoaxService.createHoax(createHoaxRequest, user), HttpStatus.CREATED);
    }

    @GetMapping("/hoaxes")
    public ResponseEntity<Page<HoaxResponse>> getHoaxes(@PageableDefault(sort = "id",
                                                                         direction = Sort.Direction.DESC) Pageable page) {
        return ResponseEntity.ok(hoaxService.getHoaxes(page));
    }

    @GetMapping("/users/{username}/hoaxes")
    public ResponseEntity<Page<HoaxResponse>> getHoaxesByUsername(@PathVariable String username,
                                                                  @PageableDefault(sort = "id",
                                                                          direction = Sort.Direction.DESC) Pageable page) {
        return ResponseEntity.ok(hoaxService.getHoaxesByUsername(username, page));
    }
}
