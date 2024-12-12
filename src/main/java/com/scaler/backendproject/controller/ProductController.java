package com.scaler.backendproject.controller;

import com.scaler.backendproject.models.Product;
import org.springframework.web.bind.annotation.*;

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
    //@RequestMapping(value = "/product", method = RequestMethod.POST)
    //Post mapping does the same thing as request mapping of post and is a shortcut
    @PostMapping("/product")
    public void createProduct(Product product) {

    }

    //This will help in "Retrieve" function
    //@RequestMapping(value = "/product", method = RequestMethod.GET)
    @GetMapping("/product")
    public Product getProductById(Long id) {
        return null;
    }

    //This will help in "Update" function
    //@RequestMapping(value = "/product", method = RequestMethod.PUT)
    @PutMapping("/product")
    public void updateProduct(Product product) {

    }

    //This will help in "Delete" function
    //@RequestMapping(value = "/product", method = RequestMethod.DELETE)
    @DeleteMapping("/product")
    public void deleteProduct(Product product) {

    }
}
