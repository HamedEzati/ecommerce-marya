package com.marya.controller;

import com.marya.controller.dto.ProductInputModel;
import com.marya.controller.dto.ProductOutputModel;
import com.marya.controller.mapper.ProductMapper;
import com.marya.entity.Category;
import com.marya.entity.Product;
import com.marya.service.CategoryService;
import com.marya.service.ProductService;
import org.springframework.stereotype.Component;

@Component
public class ProductController {

    private final ProductMapper productMapper;
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.productMapper = ProductMapper.INSTANCE;
    }

    public ProductOutputModel create(ProductInputModel productInputModel){
        Category category = categoryService.getOptionalById(productInputModel.getCategoryId()).orElseGet(() -> null);
        Product product = productMapper.productInputModelToProduct(productInputModel);
        product.setCategory(category);
        product = productService.create(product);
        return productMapper.productToProductOutputModel(product);
    }

    public void update(Long id, ProductInputModel productInputModel){
        Category category = categoryService.getOptionalById(productInputModel.getCategoryId()).orElseGet(() -> null);
        Product product = productMapper.productInputModelAndIdToProduct(productInputModel, id);
        product.setCategory(category);
        productService.update(product);
    }

    public void delete(Long id){
        productService.delete(id);
    }

}
