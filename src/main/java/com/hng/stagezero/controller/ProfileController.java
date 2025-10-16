package com.hng.stagezero.controller;

import com.hng.stagezero.dto.ProfileResponse;
import com.hng.stagezero.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;
    @GetMapping("/me")
    public ResponseEntity<ProfileResponse> getProfile(){

        ProfileResponse response = this.profileService.getProfile();
        return ResponseEntity.status(HttpStatus.OK).body( response);
    }


}
