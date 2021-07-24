package com.marya.service;

import com.marya.entity.Product;
import com.marya.service.model.PageQueryParams;
import org.springframework.data.domain.Page;

public interface ProductService {

    Product create(Product product);

    void update(Product product);

    Product getById(Long id);

    void delete(Long id);

    Page<Product> getAll(PageQueryParams pageQueryParams);

    Page<Product> getAllByCategoryId(PageQueryParams pageQueryParams, Long categoryId);

}
