package com.huongnguyen.profileservice.service;

import com.huongnguyen.profileservice.dto.request.ProfileCreationRequest;
import com.huongnguyen.profileservice.dto.response.UserProfileResponse;

import java.util.UUID;

public interface UserProfileService {

    void createProfile(ProfileCreationRequest request);
    UserProfileResponse getProfile(UUID id);
}
