package com.devband.opencomm.config;

import com.devband.opencomm.user.handler.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configurable
public class Router {

    private UserHandler userHandler;

    @Autowired
    public Router(UserHandler userHandler) {
        this.userHandler = userHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> monoRouterFunction() {
        return route(POST("/sign-up").and(accept(MULTIPART_FORM_DATA)), userHandler::signUp)
            .andRoute(POST("/auth").and(accept(APPLICATION_JSON)), userHandler::auth);
    }
}
