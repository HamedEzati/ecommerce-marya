package com.marya.controller;

import com.marya.entity.Product;
import com.marya.service.ProductService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.primefaces.model.DefaultStreamedContent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@ManagedBean
@RequestScoped
@Component
public class DownloadFile {

    private final ProductService productService;
    @Value("${files.location.upload}")
    private String uploadLocation;

    public DownloadFile(ProductService productService) {
        this.productService = productService;
    }

    public String getCsvFile() throws IOException {
        String uploadDir = uploadLocation + "csv/";
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath))
            Files.createDirectories(uploadPath);
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(uploadDir + "sample.csv"));
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                .withHeader("id", "name", "price", "quantity", "description"));
        List<Product> all = productService.getAll();
        for (Product product : all) {
            csvPrinter.printRecord(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getQuantity(),
                    product.getDescription());
        }
        csvPrinter.flush();
        csvPrinter.close();
        return "csv/sample.csv";
    }
}
