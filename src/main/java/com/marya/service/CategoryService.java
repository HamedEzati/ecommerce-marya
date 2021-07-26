package com.marya.service;

import com.marya.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Category create(Category category);

    void update(Category category);

    void delete(Category category);

    List<Category> getAll();

    Category getById(Long id);

    Optional<Category> getOptionalById(Long id);

    List<Category> getParents();

}
