package com.marya.controller.mapper;

import com.marya.controller.dto.CategoryInputModel;
import com.marya.controller.dto.CategoryOutputModel;
import com.marya.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    default CategoryOutputModel categoryToCategoryOutputModel(Category category) {
        CategoryOutputModel categoryOutputModel = new CategoryOutputModel();
        categoryOutputModel.setId(category.getId());
        categoryOutputModel.setName(category.getName());
        if (Objects.nonNull(category.getParent()))
            categoryOutputModel.setParentId(category.getParent().getId());
        if (Objects.nonNull(category.getSubcategories())){
            List<Long> subCategories = category.getSubcategories().stream().map(category1 -> category1.getId()).collect(Collectors.toList());
            categoryOutputModel.setSubcategories(subCategories);
        }
        return categoryOutputModel;
    }

    Category categoryInputModelToCategory(CategoryInputModel categoryInputModel);
}
