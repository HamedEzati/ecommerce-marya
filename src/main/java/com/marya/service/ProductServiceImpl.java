package com.marya.service;

import com.marya.entity.Product;
import com.marya.repository.ProductRepository;
import com.marya.service.exception.NotFoundException;
import com.marya.service.mapper.ProductServiceMapper;
import com.marya.service.model.PageQueryParams;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductServiceMapper productServiceMapper;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.productServiceMapper = ProductServiceMapper.INSTANCE;
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void update(Product product) {
        Product existingProduct = getById(product.getId());
        productServiceMapper.updateProduct(existingProduct, product);
        productRepository.save(existingProduct);
    }

    @Override
    public Product getById(Long id) {
        if (Objects.isNull(id))
            throw new NotFoundException("product not found.");
        else return productRepository.findById(id).orElseThrow(() -> new NotFoundException("product not found."));
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> getAll(PageQueryParams pageQueryParams) {
        return productRepository.findAll(Utils.getPageableFromPageQueryParams(pageQueryParams));
    }

    @Override
    public Page<Product> getAllByCategoryId(PageQueryParams pageQueryParams, Long categoryId) {
        return productRepository.findAllByCategoryId(Utils.getPageableFromPageQueryParams(pageQueryParams), categoryId);
    }
}
