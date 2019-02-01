package com.devband.opencomm.user.service;

import com.devband.opencomm.user.repository.UserRepository;
import com.devband.opencomm.user.to.UserTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<String> singUp(UserTO user) {
        return Mono.fromCallable(() -> "test");
    }
}
