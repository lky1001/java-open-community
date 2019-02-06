package com.devband.opencomm.post.repository;

import com.devband.opencomm.post.model.PostModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface PostRepository extends CrudRepository<PostModel, Long> {

    Page<PostModel> findByCategoryIdOrderByCreatedDesc(int categoryId, Pageable pageable);
}
