package com.marya.controller;

import com.marya.controller.dto.ProductOutputModel;
import com.marya.controller.mapper.ProductMapper;
import com.marya.entity.Product;
import com.marya.service.ProductService;
import com.marya.service.model.PageQueryParams;
import org.primefaces.model.TreeNode;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ProductPublicController {

    private final ProductMapper productMapper;
    private final ProductService productService;

    public ProductPublicController(ProductService productService) {
        this.productService = productService;
        this.productMapper = ProductMapper.INSTANCE;
    }

    private ProductOutputModel get(Long id){
        Product product = productService.getById(id);
        return productMapper.productToProductOutputModel(product);
    }

    private Page<ProductOutputModel> getAll(PageQueryParams pageQueryParams){
        return productService.getAll(pageQueryParams).map(productMapper::productToProductOutputModel);
    }

    private Page<ProductOutputModel> getAllByCategoryId(PageQueryParams pageQueryParams, Long categoryId){
        return productService.getAllByCategoryId(pageQueryParams, categoryId).map(productMapper::productToProductOutputModel);
    }

}
