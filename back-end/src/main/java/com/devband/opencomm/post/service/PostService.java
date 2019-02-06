package com.devband.opencomm.post.service;

import com.devband.opencomm.category.model.CategoryModel;
import com.devband.opencomm.category.repository.CategoryRepository;
import com.devband.opencomm.post.model.PostModel;
import com.devband.opencomm.post.repository.PostRepository;
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

    private PostRepository postRepository;
    private CategoryRepository categoryRepository;
    private Scheduler jdbcScheduler;

    @Autowired
    public PostService(PostRepository postRepository, CategoryRepository categoryRepository,
            @Qualifier("jdbcScheduler") Scheduler scheduler) {
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
}
