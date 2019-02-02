package com.devband.opencomm.support.oauth;

import com.devband.opencomm.support.oauth.model.KakaoProfile;
import com.devband.opencomm.support.oauth.model.UserProfile;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class KakaoOAuthService implements OAuthService, InitializingBean {

    private static final String KAKAO_HOST = "https://kapi.kakao.com";
    private static final String KAKAO_PROFILE_END_POINT = "/v2/user/me";

    private WebClient webClient;

    @Override
    public void afterPropertiesSet() throws Exception {
        webClient = WebClient.builder()
            .baseUrl(KAKAO_HOST)
            .build();
    }

    @Override
    public Mono<UserProfile> getUserProfile(String accessToken) {
        return webClient.get().uri(KAKAO_PROFILE_END_POINT)
            .retrieve().bodyToMono(KakaoProfile.class)
            .map(profile -> {
                UserProfile userProfile = UserProfile.builder()
                    .build();

                return userProfile;
            });
    }
}
