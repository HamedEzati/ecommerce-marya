package com.marya.service.mapper;

import com.marya.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryServiceMapper {

    CategoryServiceMapper INSTANCE = Mappers.getMapper(CategoryServiceMapper.class);

    void updateCategory(@MappingTarget Category existingCategory, Category category);

}
