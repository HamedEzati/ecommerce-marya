package com.marya.controller;

import com.marya.controller.dto.CategoryOutputModel;
import com.marya.controller.mapper.CategoryMapper;
import com.marya.entity.Category;
import com.marya.service.CategoryService;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
@ManagedBean
@RequestScoped
@Component
public class CategoryPublicController implements Serializable {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public CategoryPublicController(CategoryService categoryService) {
        this.categoryService = categoryService;
        this.categoryMapper = CategoryMapper.INSTANCE;
    }

    public CategoryOutputModel get(Long id){
        Category category = categoryService.getById(id);
        return categoryMapper.categoryToCategoryOutputModel(category);
    }

    public List<CategoryOutputModel> getAll(){
        return categoryService.getAll().stream().map(categoryMapper::categoryToCategoryOutputModel).collect(Collectors.toList());
    }

    public List<CategoryOutputModel> getParents(){
        return categoryService.getParents().stream().map(categoryMapper::categoryToCategoryOutputModel).collect(Collectors.toList());
    }

}
