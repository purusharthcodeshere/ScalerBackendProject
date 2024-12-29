package com.scaler.backendproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
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
