package com.devband.opencomm.user.service;

import com.devband.opencomm.support.oauth.OAuthService;
import com.devband.opencomm.user.model.EnumUserType;
import com.devband.opencomm.user.model.UserModel;
import com.devband.opencomm.user.repository.UserRepository;
import com.devband.opencomm.user.to.UserTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private UserRepository userRepository;

    private OAuthService kakaoOAuthService;

    @Autowired
    public UserService(UserRepository userRepository, OAuthService kakaoOAuthService) {
        this.userRepository = userRepository;
        this.kakaoOAuthService = kakaoOAuthService;
    }

    public Mono<Boolean> singUp(UserTO user) {
        return Mono.fromCallable(() -> {
            if (user.getType() == EnumUserType.KAKAO) {
                // todo - get kakao
            } else if (user.getType() == EnumUserType.EMAIL) {
                // todo - password encode
            }

            return UserModel.builder()
                .userId(user.getUserId())


                .build();
        }).map(userModel -> {
            userRepository.save(userModel);

            return true;
        });
    }
}
