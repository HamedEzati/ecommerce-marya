package com.marya.controller;

import com.marya.entity.Product;
import com.marya.service.CategoryService;
import com.marya.service.ProductService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ManagedBean
@RequestScoped
@Component
public class DownloadFile {

    private final ProductService productService;
    private final CategoryService categoryService;
    @Value("${files.location.upload}")
    private String uploadLocation;

    private Long categoryId;
    private List<SelectItem> categoriesGroup;

    public DownloadFile(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    public List<SelectItem> getCategoriesGroup() {
        categoriesGroup = new ArrayList<>();
        categoryService.getAll().forEach(category -> {
            categoriesGroup.add(new SelectItem(category.getId(),"id: " + category.getId() + " | name: " + category.getName()));
        });
        return categoriesGroup;
    }

    public void downloadCsv() throws IOException {
        String uploadDir = uploadLocation + "csv/";
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath))
            Files.createDirectories(uploadPath);
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(uploadDir + "sample.csv"));
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                .withHeader("id", "name", "price", "quantity", "description"));
        if (Objects.isNull(categoryId)) {
            List<Product> all = productService.getAll();
            for (Product product : all) {
                csvPrinter.printRecord(
                        product.getId(),
                        product.getName(),
                        product.getPrice(),
                        product.getQuantity(),
                        product.getDescription()
                );
            }
        }else {
            List<Product> all = productService.getAllByCategoryId(categoryId);
            for (Product product : all) {
                csvPrinter.printRecord(
                        product.getId(),
                        product.getName(),
                        product.getPrice(),
                        product.getQuantity(),
                        product.getDescription()
                );
            }
        }
        csvPrinter.flush();
        csvPrinter.close();
        categoryId = null;
        ExternalContext ec = FacesContext.getCurrentInstance()
                .getExternalContext();
        try {
            ec.redirect(ec.getRequestContextPath()
                    + "/csv/sample.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
