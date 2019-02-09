package com.devband.opencomm.config;

import com.devband.opencomm.post.handler.PostHandler;
import com.devband.opencomm.user.handler.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class Router {

    private UserHandler userHandler;
    private PostHandler postHandler;

    @Autowired
    public Router(UserHandler userHandler, PostHandler postHandler) {
        this.userHandler = userHandler;
        this.postHandler = postHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> monoRouterFunction() {
        return route(POST("/sign-up").and(accept(MULTIPART_FORM_DATA)), userHandler::signUp)
            .andRoute(GET("/api/v1/posts/{category}").and(accept(APPLICATION_JSON)), postHandler::getPosts)
            .andRoute(POST("/api/v1/posts").and(accept(APPLICATION_JSON)), postHandler::writePost);
    }
}
