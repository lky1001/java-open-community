package com.devband.opencomm.security;

import com.devband.opencomm.user.model.UserModel;
import com.devband.opencomm.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsPasswordService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collections;

@Service
public class CustomUserDetailService implements ReactiveUserDetailsService, ReactiveUserDetailsPasswordService {

    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Mono<UserDetails> updatePassword(UserDetails user, String newPassword) {
        return null;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return Mono.fromCallable(() -> {
            UserModel user = userRepository.findByUserId(username);

            return CustomUserDetails.builder()
                .username(user.getUserId())
                .password(user.getPassword())
                .roles(Collections.singletonList(Role.ROLE_USER))
                .build();
        });
    }
}
