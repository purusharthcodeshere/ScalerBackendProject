package com.scaler.backendproject.service;

import com.scaler.backendproject.exceptions.CategoryNotFoundException;
import com.scaler.backendproject.models.Category;
import com.scaler.backendproject.models.Product;

import java.util.List;

public interface CategoryService {
    Category createCategory(Long id, String categoryTitle);
    List<Product> getProductsInCategory(String category) throws CategoryNotFoundException;
    List<Category> getAllCategories() throws NullPointerException;
}
