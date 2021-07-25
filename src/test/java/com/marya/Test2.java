package com.marya;

import com.marya.entity.Category;
import com.marya.repository.CategoryRepository;
import com.marya.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Test2 {
    @Autowired
    private CategoryService categoryService;
    @Test
    public void te(){
        Category byId = categoryService.getById(1L);
        Category category1 = new Category();
        category1.setName("category1.1");
        category1.setParent(byId);
        Category category2 = new Category();
        category2.setName("category1.2");
        category2.setParent(byId);
        categoryService.create(category1);
        categoryService.create(category2);

    }
}
