package com.and1ss.user_service.service.impl.repos;

import com.and1ss.user_service.service.model.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("access_token")
public interface AccessTokenRepository extends JpaRepository<AccessToken, UUID> {
    AccessToken findAccessTokenByUserId(UUID userId);
    AccessToken findAccessTokenByToken(UUID token);
}
