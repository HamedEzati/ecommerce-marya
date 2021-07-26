package com.marya.controller;

import com.marya.controller.dto.CategoryInputModel;
import com.marya.controller.dto.CategoryOutputModel;
import com.marya.controller.mapper.CategoryMapper;
import com.marya.entity.Category;
import com.marya.service.CategoryService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ManagedBean
@RequestScoped
@Component
@Named
@ViewScoped
public class CategoryPublicController implements Serializable {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;


    private CategoryInputModel categoryInputModel = new CategoryInputModel();
    private CategoryOutputModel categoryOutputModel = new CategoryOutputModel();

    public CategoryPublicController(CategoryService categoryService) {
        this.categoryService = categoryService;
        this.categoryMapper = CategoryMapper.INSTANCE;
    }

    public CategoryOutputModel get(Long id) {
        Category category = categoryService.getById(id);
        return categoryMapper.categoryToCategoryOutputModel(category);
    }

    public List<CategoryOutputModel> getAll() {
        return categoryService.getAll().stream().map(categoryMapper::categoryToCategoryOutputModel).collect(Collectors.toList());
    }

    public List<CategoryOutputModel> getParents() {
        return categoryService.getParents().stream().map(categoryMapper::categoryToCategoryOutputModel).collect(Collectors.toList());
    }

    public CategoryOutputModel create() {
        Category parent = categoryService.getOptionalById(categoryInputModel.getParentId()).orElseGet(() -> null);
        Category category = categoryMapper.categoryInputModelToCategory(categoryInputModel);
        category.setParent(parent);
        category = categoryService.create(category);
        return categoryMapper.categoryToCategoryOutputModel(category);
    }

    public void update(CategoryOutputModel categoryOutputModel) {
            Category category = new Category();
            if (Objects.nonNull(categoryOutputModel.getParentId())) {
                Category parent = categoryService.getOptionalById(categoryOutputModel.getParentId()).orElseGet(() -> null);
                category.setParent(parent);
            }
            category.setId(categoryOutputModel.getId());
            category.setName(categoryOutputModel.getName());
            categoryService.create(category);
    }

    public void delete(CategoryOutputModel categoryOutputModel) {
        Category category = categoryService.getById(categoryOutputModel.getId());
        categoryService.delete(category);
    }

    public CategoryInputModel getCategoryInputModel() {
        return categoryInputModel;
    }

    public void setCategoryInputModel(CategoryInputModel categoryInputModel) {
        this.categoryInputModel = categoryInputModel;
    }

}
