package com.devband.opencomm.user.repository;

import com.devband.opencomm.user.model.UserModel;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends CrudRepository<UserModel, Integer> {
}
