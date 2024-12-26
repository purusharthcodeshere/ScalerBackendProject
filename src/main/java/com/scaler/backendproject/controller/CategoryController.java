package com.scaler.backendproject.controller;

import com.scaler.backendproject.exceptions.CategoryNotFoundException;
import com.scaler.backendproject.models.Category;
import com.scaler.backendproject.models.Product;
import com.scaler.backendproject.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class CategoryController {

    //Implementing 2 Category APIs
    //Get in a Category
    //get All categories

    //Object of CategoryService
    //This now has dependency on the CategoryService
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/category")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category newCreatedCategory = categoryService.createCategory(category.getId(), category.getTitle());

        return new ResponseEntity<>(newCreatedCategory, HttpStatus.CREATED);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        //No need to create an object of list of categories
        //We can directly pass it in the response entity
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/productsInCategory")
    public ResponseEntity<List<Product>> getProductsByCategory(@RequestBody Category category) throws CategoryNotFoundException {
        String categoryTitle = category.getTitle();
        List<Product> productsInCategory = categoryService.getProductsInCategory(categoryTitle);
        return new ResponseEntity<>(productsInCategory, HttpStatus.OK);
    }
}
