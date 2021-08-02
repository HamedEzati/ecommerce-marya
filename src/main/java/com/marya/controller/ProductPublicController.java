package com.marya.controller;

import com.marya.controller.dto.ProductInputModel;
import com.marya.controller.dto.ProductOutputModel;
import com.marya.controller.mapper.ProductMapper;
import com.marya.entity.Category;
import com.marya.entity.Product;
import com.marya.service.CategoryService;
import com.marya.service.ProductService;
import lombok.Data;
import lombok.SneakyThrows;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean
@RequestScoped
@Component
@Data
public class ProductPublicController {

    private final ProductMapper productMapper;
    private final ProductService productService;
    private final CategoryService categoryService;
    private String card;

    private List<ProductOutputModel> allProduct;
    private ProductInputModel productInputModel = new ProductInputModel();
    private ProductOutputModel productOutputModel = new ProductOutputModel();

    public ProductPublicController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.productMapper = ProductMapper.INSTANCE;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public ProductOutputModel create() {
        Category category = categoryService.getOptionalById(productInputModel.getCategoryId()).orElseGet(() -> null);
        Product product = productMapper.productInputModelToProduct(productInputModel);
        product.setCategory(category);
        product = productService.create(product);
        return productMapper.productToProductOutputModel(product);
    }

    public void update() {
        allProduct.stream().forEach(productOutputModel -> {
            Category category = categoryService.getOptionalById(productOutputModel.getCategoryId()).orElseGet(() -> null);
            Product product = productMapper.productOutputToProduct(productOutputModel);
            product.setCategory(category);
            productService.update(product);
        });
    }

    public void delete(Long id) {
        productService.delete(id);
    }

    public ProductOutputModel get(Long id) {
        FacesMessage message = new FacesMessage("Successful");
        FacesContext.getCurrentInstance().addMessage(null, message);
        Product product = productService.getById(id);
        return productMapper.productToProductOutputModel(product);
    }

    public List<ProductOutputModel> getAll() {
        return productService.getAll().stream().map(productMapper::productToProductOutputModel).collect(Collectors.toList());
    }

    public List<ProductOutputModel> getAllProduct() {
        allProduct = productService.getAll().stream().map(productMapper::productToProductOutputModel).collect(Collectors.toList());
        return allProduct;
    }

    public List<ProductOutputModel> getAllByCategoryId(Long categoryId) {
        return productService.getAllByCategoryId(categoryId).stream().map(productMapper::productToProductOutputModel).collect(Collectors.toList());
    }


}
