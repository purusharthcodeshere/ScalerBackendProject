package com.scaler.backendproject;

import com.scaler.backendproject.models.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendProjectApplication.class, args);

        Product product = new Product();
        product.setId(12L);
        System.out.println(product.toString());
    }
}
