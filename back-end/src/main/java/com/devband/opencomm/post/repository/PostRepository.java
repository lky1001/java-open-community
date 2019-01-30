package com.devband.opencomm.post.repository;

import com.devband.opencomm.post.model.PostModel;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface PostRepository extends CrudRepository<PostModel, Long> {
}
