package com.scaler.backendproject.service;

import com.scaler.backendproject.exceptions.ProductNotFoundException;
import com.scaler.backendproject.models.Category;
import com.scaler.backendproject.models.Product;
import org.springframework.data.domain.Page;

public interface ProductService {
    Product getSingleProduct(Long id) throws ProductNotFoundException;
    Page<Product> getAllProducts(int pageNumber, int pageSize, String fieldName) throws ProductNotFoundException;
    Product createProduct(Long id, String title, String description, Double price, String category, String imageUrl);
    Product deleteProduct(Long id) throws ProductNotFoundException;
    Product updateProduct(Long id, String title, String description, Double price, Category category, String imageUrl) throws ProductNotFoundException;
    //Product[] getAllProducts();
}
