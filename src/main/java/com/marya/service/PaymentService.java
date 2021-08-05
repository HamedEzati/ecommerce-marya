package com.marya.service;

import com.marya.entity.Category;

import java.util.List;
import java.util.Optional;

public interface PaymentService {

    void pay(List<Long> products);
}
