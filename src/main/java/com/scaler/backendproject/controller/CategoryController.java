package com.scaler.backendproject.controller;

import com.scaler.backendproject.exceptions.CategoryNotFoundException;
import com.scaler.backendproject.models.Category;
import com.scaler.backendproject.models.Product;
import com.scaler.backendproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    //Implementing 2 Category APIs
    //Get in a Category
    //get All categories

    //Object of CategoryService
    //This now has dependency on the CategoryService
    private CategoryService categoryService;

    public CategoryController(@Qualifier("selfCategoryService") CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/category")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category newCreatedCategory = categoryService.createCategory(category.getTitle());
        return new ResponseEntity<>(newCreatedCategory, HttpStatus.CREATED);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        //No need to create an object of list of categories
        //We can directly pass it in the response entity
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/productsInCategory/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable("category") String category) throws CategoryNotFoundException {
        List<Product> productsInCategory = categoryService.getProductsInCategory(category);
        return new ResponseEntity<>(productsInCategory, HttpStatus.OK);
    }
}
