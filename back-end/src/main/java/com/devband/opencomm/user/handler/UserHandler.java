package com.devband.opencomm.user.handler;

import com.devband.opencomm.security.CustomUserDetails;
import com.devband.opencomm.security.JWTUtil;
import com.devband.opencomm.security.PBKDF2Encoder;
import com.devband.opencomm.security.Role;
import com.devband.opencomm.security.model.AuthRequest;
import com.devband.opencomm.security.model.AuthResponse;
import com.devband.opencomm.user.service.UserService;
import com.devband.opencomm.user.to.UserTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Arrays;

import static org.springframework.web.reactive.function.BodyExtractors.toMultipartData;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Component
public class UserHandler {

    private JWTUtil jwtUtil;

    private PBKDF2Encoder passwordEncoder;

    private UserService userService;

    @Autowired
    public UserHandler(JWTUtil jwtUtil, PBKDF2Encoder passwordEncoder, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    public Mono<ServerResponse> auth(ServerRequest serverRequest) {
        Mono<AuthResponse> response = serverRequest.bodyToMono(AuthRequest.class)
            .map(authRequest -> {
                // todo - login user
                CustomUserDetails userDetails = new CustomUserDetails("test");
                userDetails.setEnabled(true);
                userDetails.setRoles(Arrays.asList(Role.ROLE_USER));

                return new AuthResponse(jwtUtil.generateToken(userDetails));
            });

        return ServerResponse.ok().body(response, AuthResponse.class);
    }

    public Mono<ServerResponse> signUp(ServerRequest request) {
        return request.bodyToMono(UserTO.class)
            .map(userService::singUp)
            .flatMap(result -> ServerResponse.ok().body(fromObject(result)));
    }
}
