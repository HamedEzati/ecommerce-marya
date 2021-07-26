package com.marya.controller.mapper;

import com.marya.controller.dto.ProductInputModel;
import com.marya.controller.dto.ProductOutputModel;
import com.marya.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product productInputModelToProduct(ProductInputModel productInputModel);
    @Mapping(source = "product.category.id", target = "categoryId")
    ProductOutputModel productToProductOutputModel(Product product);

    Product productInputModelAndIdToProduct(ProductInputModel productInputModel, Long id);
}
