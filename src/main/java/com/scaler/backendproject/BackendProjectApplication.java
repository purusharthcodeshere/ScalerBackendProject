package com.scaler.backendproject;

import com.scaler.backendproject.models.Category;
import com.scaler.backendproject.models.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendProjectApplication.class, args);

//        Product product = new Product("Test title", "Test Description", 822.00,
//                "unknown image url", new Category("Test Category"));
//        product.setId(8378L);
//        System.out.println(product.getId());
//        System.out.println(product.toString());
    }
}
