package com.marya.controller;

import com.marya.controller.dto.CategoryInputModel;
import com.marya.controller.dto.CategoryOutputModel;
import com.marya.controller.mapper.CategoryMapper;
import com.marya.entity.Category;
import com.marya.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
        this.categoryMapper = CategoryMapper.INSTANCE;
    }

    public CategoryOutputModel create(CategoryInputModel categoryInputModel){
        Category parent = categoryService.getOptionalById(categoryInputModel.getParentId()).orElseGet(() -> null);
        Category category = categoryMapper.categoryInputModelToCategory(categoryInputModel);
        category.setParent(parent);
        category = categoryService.create(category);
        return categoryMapper.categoryToCategoryOutputModel(category);
    }

    public void update(Long id, CategoryInputModel categoryInputModel){
        Category parent = categoryService.getOptionalById(categoryInputModel.getParentId()).orElseGet(() -> null);
        Category category = categoryMapper.categoryInputModelToCategory(categoryInputModel);
        category.setParent(parent);
        categoryService.create(category);
    }

    public void delete(Long id){
        categoryService.delete(id);
    }

}
