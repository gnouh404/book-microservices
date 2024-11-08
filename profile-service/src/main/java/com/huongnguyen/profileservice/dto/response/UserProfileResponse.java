package com.huongnguyen.profileservice.dto.response;

public record UserProfileResponse(java.util.UUID id, String firstName,
                                  String lastName, String city) {
}
