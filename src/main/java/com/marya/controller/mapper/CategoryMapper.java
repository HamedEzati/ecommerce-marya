package com.marya.controller.mapper;

import com.marya.controller.dto.CategoryInputModel;
import com.marya.controller.dto.CategoryOutputModel;
import com.marya.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryOutputModel categoryToCategoryOutputModel(Category category);

    Category categoryInputModelToCategory(CategoryInputModel categoryInputModel);
}
