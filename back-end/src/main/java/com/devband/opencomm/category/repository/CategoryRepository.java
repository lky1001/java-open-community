package com.devband.opencomm.category.repository;

import com.devband.opencomm.category.model.CategoryModel;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface CategoryRepository extends CrudRepository<CategoryModel, Integer> {

    CategoryModel findByCategoryAndDeletedIsNull(String category);
}
