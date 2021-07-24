package com.marya.service.mapper;

import com.marya.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductServiceMapper {

    ProductServiceMapper INSTANCE = Mappers.getMapper(ProductServiceMapper.class);

    void updateProduct(@MappingTarget Product existingProduct, Product product);

}
