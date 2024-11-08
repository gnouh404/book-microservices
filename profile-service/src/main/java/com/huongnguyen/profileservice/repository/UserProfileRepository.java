package com.huongnguyen.profileservice.repository;

import com.huongnguyen.profileservice.entity.UserProfile;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserProfileRepository extends Neo4jRepository<UserProfile, UUID> {
}
