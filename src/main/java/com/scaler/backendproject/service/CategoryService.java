package com.scaler.backendproject.service;

import com.scaler.backendproject.models.Category;
import com.scaler.backendproject.models.Product;

import java.util.List;
import java.util.NoSuchElementException;

public interface CategoryService {
    Category createCategory(String categoryTitle);
    List<Product> getProductsInCategory(String category) throws NoSuchElementException;
    List<Category> getAllCategories() throws NullPointerException;
}
