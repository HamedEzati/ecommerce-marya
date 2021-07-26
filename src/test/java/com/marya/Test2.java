package com.marya;
import com.marya.entity.Category;
import com.marya.entity.Product;
import com.marya.repository.CategoryRepository;
import com.marya.service.CategoryService;
import com.marya.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Test2 {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Test
    public void te(){
        Category byId = categoryService.getById(2L);
//        Category category1 = new Category();
//        category1.setName("category1.1.1");
//        category1.setParent(byId);
//        Category category2 = new Category();
//        category2.setName("category1.1.2");
//        category2.setParent(byId);
//        categoryService.create(category1);
//        categoryService.create(category2);
        for (int i = 1 ; i < 6 ; i++){
            Product product = new Product();
            product.setName("product"+i);
            product.setCategory(byId);
            product.setDescription("descriptasdion2asdada135464318");
            product.setPrice(2500);
            product.setQuantity(50);
            product.setImageUrl("https://thecare.com.sg/wp-content/uploads/2021/02/lenyes_lenyes_lh93_pro_wireless_bluetooth_stereo_hifi_heavy_bass_headphone_headset_nyaman_original_garansi_full01_gtzcjxta.jpg");
            productService.create(product);
        }

    }
}
