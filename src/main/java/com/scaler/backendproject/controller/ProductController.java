package com.scaler.backendproject.controller;

import com.scaler.backendproject.models.Product;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    //CRUD APIs around Controller
    /*For the product
    * 1. Create a product
    * 2. Get a product
    * 3. Update a product
    * 4. Delete a product
    */

    //This will help in performing "Create" function
//    @RequestMapping(value = "/product", method = RequestMethod.POST)
    //Post mapping does the same thing as request mapping of post and is a shortcut
    @PostMapping("/product")
    public void createProduct(Product product) {

    }

    //This will help in "Retrieve" function
    public Product getProductById(Long id) {
        return null;
    }

    //This will help in "Update" function
    public void updateProduct(Product product) {

    }

    //This will help in "Delete" function
    public void deleteProduct(Product product) {

    }
}
