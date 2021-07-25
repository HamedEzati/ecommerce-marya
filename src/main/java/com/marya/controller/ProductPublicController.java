package com.marya.controller;

import com.marya.controller.dto.ProductOutputModel;
import com.marya.controller.mapper.ProductMapper;
import com.marya.entity.Product;
import com.marya.service.ProductService;
import com.marya.service.model.PageQueryParams;
import org.primefaces.model.TreeNode;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean
@RequestScoped
@Component
public class ProductPublicController {

    private final ProductMapper productMapper;
    private final ProductService productService;

    public ProductPublicController(ProductService productService) {
        this.productService = productService;
        this.productMapper = ProductMapper.INSTANCE;
    }

    public ProductOutputModel get(Long id){
        Product product = productService.getById(id);
        return productMapper.productToProductOutputModel(product);
    }

    public List<ProductOutputModel> getAll(){
        return productService.getAll().stream().map(productMapper::productToProductOutputModel).collect(Collectors.toList());
    }

    public List<ProductOutputModel> getAllByCategoryId(Long categoryId){
        return productService.getAllByCategoryId(categoryId).stream().map(productMapper::productToProductOutputModel).collect(Collectors.toList());
    }

}
