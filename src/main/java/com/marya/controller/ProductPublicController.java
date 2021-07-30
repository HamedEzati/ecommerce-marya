package com.marya.controller;

import com.marya.controller.dto.ProductInputModel;
import com.marya.controller.dto.ProductOutputModel;
import com.marya.controller.mapper.ProductMapper;
import com.marya.entity.Category;
import com.marya.entity.Product;
import com.marya.service.CategoryService;
import com.marya.service.ProductService;
import lombok.Data;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.file.UploadedFileWrapper;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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


    private List<ProductOutputModel> allProduct;
    private ProductInputModel productInputModel = new ProductInputModel();
    private ProductOutputModel productOutputModel = new ProductOutputModel();


    public ProductPublicController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.productMapper = ProductMapper.INSTANCE;
    }



    public ProductOutputModel create(){
        Category category = categoryService.getOptionalById(productInputModel.getCategoryId()).orElseGet(() -> null);
        Product product = productMapper.productInputModelToProduct(productInputModel);
        product.setCategory(category);
        product = productService.create(product);
        return productMapper.productToProductOutputModel(product);
    }

    public void update(){
        allProduct.stream().forEach(productOutputModel -> {
            Category category = categoryService.getOptionalById(productOutputModel.getCategoryId()).orElseGet(() -> null);
            Product product = productMapper.productOutputToProduct(productOutputModel);
            product.setCategory(category);
            productService.update(product);
        });
    }

    public void delete(Long id){
        productService.delete(id);
    }

    public ProductOutputModel get(Long id){
        Product product = productService.getById(id);
        return productMapper.productToProductOutputModel(product);
    }

    public List<ProductOutputModel> getAll(){
        return productService.getAll().stream().map(productMapper::productToProductOutputModel).collect(Collectors.toList());
    }

    public List<ProductOutputModel> getAllProduct(){
        allProduct = productService.getAll().stream().map(productMapper::productToProductOutputModel).collect(Collectors.toList());
        return allProduct;
    }

    public List<ProductOutputModel> getAllByCategoryId(Long categoryId){
        return productService.getAllByCategoryId(categoryId).stream().map(productMapper::productToProductOutputModel).collect(Collectors.toList());
    }


    public void exportCsv() throws IOException {
        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get("./sample.csv"));

                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("ID", "Name", "Designation", "Company"));
        ) {
            csvPrinter.printRecord("1", "Sundar Pichai ♥", "CEO", "Google");
            csvPrinter.printRecord("2", "Satya Nadella", "CEO", "Microsoft");
            csvPrinter.printRecord("3", "Tim cook", "CEO", "Apple");

            csvPrinter.printRecord(Arrays.asList("4", "Mark Zuckerberg", "CEO", "Facebook"));

            csvPrinter.flush();
        }
    }


}
