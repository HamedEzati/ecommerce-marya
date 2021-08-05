package com.marya.service;

import com.marya.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final ProductService productService;

    public PaymentServiceImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void pay(List<Long> products) {
        products.forEach(id -> {
            Product product = productService.getById(id);
            if (product.getQuantity() > 0){
                product.setQuantity(product.getQuantity() - 1);
                productService.update(product);
            }
        });
    }
}
