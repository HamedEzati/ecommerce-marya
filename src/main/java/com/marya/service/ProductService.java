package com.marya.service;

import com.marya.entity.Product;
import com.marya.service.model.PageQueryParams;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    Product create(Product product);

    void update(Product product);

    Product getById(Long id);

    void delete(Long id);

    List<Product> getAll();

    List<Product> getAllByCategoryId(Long categoryId);

}
