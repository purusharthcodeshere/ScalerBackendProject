package com.scaler.backendproject.service;

import com.scaler.backendproject.dto.FakeStoreProductDTO;
import com.scaler.backendproject.models.Category;
import com.scaler.backendproject.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long id);
    List<Product> getAllProducts();
    Product createProduct(Long id, String title, String description, Double price, String category, String imageUrl);
    Product deleteProduct(Long id);
    Product updateProduct(Long id, String title, String description, Double price, Category category, String imageUrl);
//    Product[] getAllProducts();
}
