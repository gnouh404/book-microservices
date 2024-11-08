package com.huongnguyen.profileservice.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huongnguyen.profileservice.dto.request.ProfileCreationRequest;
import com.huongnguyen.profileservice.dto.response.UserProfileResponse;
import com.huongnguyen.profileservice.entity.UserProfile;
import com.huongnguyen.profileservice.repository.UserProfileRepository;
import com.huongnguyen.profileservice.service.UserProfileService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("userProfileServiceImpl")
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final ObjectMapper objectMapper;

    public UserProfileServiceImpl(UserProfileRepository userProfileRepository, ObjectMapper objectMapper) {
        this.userProfileRepository = userProfileRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void createProfile(ProfileCreationRequest request){
        if (request.getFirstName() == null || request.getLastName() == null ||
                request.getCity() == null) {
            throw new IllegalArgumentException("Invalid input data");
        }

        // mapping request data to UserProfile
        UserProfile userProfile = objectMapper.convertValue(request, UserProfile.class);

        userProfileRepository.save(userProfile);
    }

    @Override
    public UserProfileResponse getProfile(UUID id) {
        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        return new UserProfileResponse(userProfile.getId(), userProfile.getFirstName(),
                userProfile.getLastName(), userProfile.getCity());
    }
}
