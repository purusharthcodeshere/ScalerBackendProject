package com.scaler.backendproject.service;

import com.scaler.backendproject.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

//This annotation here is going to tell SpringBoot that this is one of our important class so create an object of this
@Service
public class FakeStoreProductService implements ProductService {

    //Inside this, fake store is going to be third party service

    @Override
    public Product getSingleProduct(long id) {
        System.out.println("We are int single product method");
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }
}
