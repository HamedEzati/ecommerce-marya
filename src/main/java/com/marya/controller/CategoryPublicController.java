package com.marya.controller;

import com.marya.controller.dto.CategoryOutputModel;
import com.marya.controller.mapper.CategoryMapper;
import com.marya.entity.Category;
import com.marya.service.CategoryService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryPublicController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public CategoryPublicController(CategoryService categoryService) {
        this.categoryService = categoryService;
        this.categoryMapper = CategoryMapper.INSTANCE;
    }

    private CategoryOutputModel get(Long id){
        Category category = categoryService.getById(id);
        return categoryMapper.categoryToCategoryOutputModel(category);
    }

    private List<CategoryOutputModel> getAll(){
        return categoryService.getAll().stream().map(categoryMapper::categoryToCategoryOutputModel).collect(Collectors.toList());
    }

}
