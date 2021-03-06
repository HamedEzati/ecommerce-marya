package com.marya.service;

import com.marya.entity.Category;
import com.marya.repository.CategoryRepository;
import com.marya.service.exception.NotFoundException;
import com.marya.service.exception.ValidationException;
import com.marya.service.mapper.CategoryServiceMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryServiceMapper categoryServiceMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryServiceMapper = CategoryServiceMapper.INSTANCE;
    }

    @Override
    public Category create(Category category) {
        if (Objects.isNull(category.getName()) || category.getName().isEmpty())
            throw new ValidationException("category name is empty.");
        return categoryRepository.save(category);
    }

    @Override
    public void update(Category category) {
        if (Objects.isNull(category.getName()) || category.getName().isEmpty())
            throw new ValidationException("category name is empty.");
        Category existingCategory = getById(category.getId());
        categoryServiceMapper.updateCategory(existingCategory, category);
        categoryRepository.save(existingCategory);
    }

    @Override
    public void delete(Long id) {
        if (Objects.isNull(id))
            throw new NotFoundException("category not found.");
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getById(Long id) {
        if (Objects.isNull(id))
            return new Category();
        else return categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("category not found."));
    }

    @Override
    public Optional<Category> getOptionalById(Long id) {
        if (Objects.isNull(id))
            return Optional.empty();
        else return categoryRepository.findById(id);
    }

    @Override
    public List<Category> getParents() {
        return categoryRepository.findAllByParent(null);
    }

    @Override
    public List<Category> getByParentCategory(Category category) {
        return categoryRepository.findAllByParent(category);
    }

}
