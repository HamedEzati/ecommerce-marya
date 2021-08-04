package com.marya.controller;

import com.marya.controller.dto.MessagesView;
import com.marya.entity.Product;
import com.marya.service.ProductService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
@Component
public class UploadFile {

    private UploadedFile file;
    private Long productId;
    private List<SelectItem> productsGroup;
    private final ProductService productService;
    @Value("${files.location.upload}")
    private String uploadLocation;

    public UploadFile(ProductService productService) {
        this.productService = productService;
    }

    public List<SelectItem> getProductsGroup() {
        productsGroup = new ArrayList<>();
        productService.getAll().forEach(product -> {
            productsGroup.add(new SelectItem(product.getId(),"id: " + product.getId() + " | name: " + product.getName()));
        });
        return productsGroup;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload() throws IOException {
        if (file != null && productId != null) {
            Product product = productService.getById(productId);
            String uploadDir = uploadLocation + "images/";
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath))
                Files.createDirectories(uploadPath);
            String filename = FilenameUtils.getName(file.getFileName());
            InputStream input = file.getInputstream();
            OutputStream output = new FileOutputStream(new File(uploadDir, filename));

            try {
                IOUtils.copy(input, output);
                product.setImageUrl("images/" + filename);
                productService.update(product);
                MessagesView.updateMessage();
                productId = null;
            } finally {
                IOUtils.closeQuietly(input);
                IOUtils.closeQuietly(output);
            }
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }


}
