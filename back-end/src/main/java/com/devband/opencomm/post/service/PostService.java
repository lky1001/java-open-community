package com.devband.opencomm.post.service;

import com.devband.opencomm.category.model.CategoryModel;
import com.devband.opencomm.category.repository.CategoryRepository;
import com.devband.opencomm.post.model.PostModel;
import com.devband.opencomm.post.repository.PostRepository;
import com.devband.opencomm.response.ResponseCode;
import com.devband.opencomm.user.model.UserModel;
import com.devband.opencomm.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.util.annotation.NonNull;

import java.util.Collections;

@Service
public class PostService {

    private UserRepository userRepository;
    private PostRepository postRepository;
    private CategoryRepository categoryRepository;
    private Scheduler jdbcScheduler;

    @Autowired
    public PostService(UserRepository userRepository, PostRepository postRepository, CategoryRepository categoryRepository,
            @Qualifier("jdbcScheduler") Scheduler scheduler) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
        this.jdbcScheduler = scheduler;
    }

    public Mono<Page<PostModel>> getPosts(@NonNull String category, @NonNull Pageable pageable) {
        // todo - category cache
        return Mono.fromCallable(() -> categoryRepository.findByCategoryAndDeletedIsNull(category))
            .subscribeOn(jdbcScheduler)
            .onErrorReturn(new CategoryModel())
            .map(categoryModel -> {
                if (categoryModel.getId() > 0) {
                    return postRepository.findByCategoryIdOrderByCreatedDesc(categoryModel.getId(), pageable);
                } else {
                    return new PageImpl(Collections.emptyList());
                }
            });
    }

    public Mono<Integer> writePost(PostModel post) {
        // todo - auth
        return Mono.fromCallable(() -> {
            UserModel user = userRepository.findById(1).orElse(null);
            CategoryModel category = categoryRepository.findById(post.getCategoryId()).orElse(null);

            if (user == null || category == null) {
                return ResponseCode.ERROR_AUTH;
            }

            post.setUser(user);
            post.setCategory(category);

            postRepository.save(post);

            return ResponseCode.SUCCESS;
        })
        .subscribeOn(jdbcScheduler);
    }
}
