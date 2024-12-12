package com.scaler.backendproject.service;

import com.scaler.backendproject.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(long id);
    List<Product> getAllProducts();
    Product createProduct(Product product);
}
