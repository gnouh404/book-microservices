package com.huongnguyen.profileservice.controller;

import com.huongnguyen.profileservice.dto.request.ProfileCreationRequest;
import com.huongnguyen.profileservice.dto.response.ApiResponse;
import com.huongnguyen.profileservice.dto.response.UserProfileResponse;
import com.huongnguyen.profileservice.service.UserProfileService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserProfileController {

    private final UserProfileService userProfileService;

    public UserProfileController(@Qualifier("userProfileServiceImpl") UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createUserProfile(@RequestBody ProfileCreationRequest request) {
        try {
            userProfileService.createProfile(request);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ApiResponse(HttpStatus.CREATED.value(), "Profile created successfully"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred"));
        }
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<UserProfileResponse> getUserProfile(@PathVariable UUID profileId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userProfileService.getProfile(profileId));
    }
}
