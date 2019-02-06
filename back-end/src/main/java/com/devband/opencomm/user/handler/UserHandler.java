package com.devband.opencomm.user.handler;

import com.devband.opencomm.user.service.UserService;
import com.devband.opencomm.user.to.UserTO;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Component
public class UserHandler {

    private UserService userService;

    public Mono<ServerResponse> signUp(ServerRequest request) {
        return request.bodyToMono(UserTO.class)
            .map(userService::singUp)
            .flatMap(result -> ServerResponse.ok().body(fromObject(result)));
    }
}
