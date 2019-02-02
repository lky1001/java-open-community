package com.devband.opencomm.support.oauth;

import com.devband.opencomm.support.oauth.model.UserProfile;
import reactor.core.publisher.Mono;

public interface OAuthService {

    Mono<UserProfile> getUserProfile(String accessToken);
}
