package com.devband.opencomm.post.handler;

import com.devband.opencomm.post.model.PostModel;
import com.devband.opencomm.post.service.PostService;
import com.devband.opencomm.response.PageModel;
import com.devband.opencomm.response.Post;
import com.devband.opencomm.response.Response;
import com.devband.opencomm.util.PageUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.lang.reflect.Type;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.ServerResponse.noContent;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class PostHandler {

    private PostService postService;
    private ModelMapper modelMapper;
    private Scheduler handlerScheduler;

    @Autowired
    public PostHandler(PostService postService, ModelMapper modelMapper, @Qualifier("handlerScheduler") Scheduler scheduler) {
        this.postService = postService;
        this.modelMapper = modelMapper;
        this.handlerScheduler = scheduler;
    }

    public Mono<ServerResponse> getPosts(ServerRequest request) {
        String category = request.pathVariable("category");

        Pageable pageable = PageUtils.parsePageable(request);

        return postService.getPosts(category, pageable)
            .subscribeOn(handlerScheduler)
            .map(page -> {
                Type listType = new TypeToken<List<Post>>() {}.getType();

                List<Object> postList = modelMapper.map(page.getContent(), listType);

                return PageModel.of(page, postList);
            })
            .flatMap(page -> ok().contentType(APPLICATION_JSON)
                .body(fromObject(page))
                .switchIfEmpty(noContent().build()));
    }

    public Mono<ServerResponse> writePost(ServerRequest request) {
        return request.bodyToMono(PostModel.class)
            .flatMap(post -> postService.writePost(post))
            .flatMap(responseCode -> ok().contentType(APPLICATION_JSON)
                .body(fromObject(Response.builder().code(responseCode).build()))
                .switchIfEmpty(noContent().build()));
    }
}
