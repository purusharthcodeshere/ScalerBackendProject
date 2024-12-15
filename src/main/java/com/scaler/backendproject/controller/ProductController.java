package com.scaler.backendproject.controller;

import com.scaler.backendproject.models.Product;
import com.scaler.backendproject.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    //CRUD APIs around Controller
    /*For the product
    * 1. Create a product
    * 2. Get a product
    * 3. Update a product
    * 4. Delete a product
    */

    //This product controller has now the dependency on the productService
    private ProductService productService;

    //Constructor to pass service to pass in controller so we can implement it
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //This will help in performing "Create" function
    //@RequestMapping(value = "/product", method = RequestMethod.POST)
    //Post mapping does the same thing as request mapping of post and is a shortcut
    @PostMapping("/product")
    public Product createProduct(@RequestBody Product product) {
        //Local variable 'p' was redundant
        return productService.createProduct(product.getId(),
                product.getTitle(), product.getDescription(),
                product.getPrice(), product.getCategory().getTitle(),
                product.getImageUrl());
    }

    //This will help in "Retrieve" function
    //@RequestMapping(value = "/product", method = RequestMethod.GET)
    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        System.out.println("Starting the getSingleProduct API here");
        Product p = productService.getSingleProduct(id);
        System.out.println("Ending the API here");
        return p;
    }

    @GetMapping("/product")
    public List<Product> getAllProducts() {
        System.out.println("Starting the Get all Products API here");
        List<Product> p = productService.getAllProducts();
        System.out.println("Ending the Get All products API");

        return p;
    }

    //This will help in "Update" function
    //@RequestMapping(value = "/product", method = RequestMethod.PUT)
    @PutMapping("/product")
    public Product updateProduct(Product product) {
        return null;
    }

    //This will help in "Delete" function
    //@RequestMapping(value = "/product", method = RequestMethod.DELETE)
    @DeleteMapping("/product/{id}")
    public Product deleteProduct(@PathVariable("id") Long id) {
        System.out.println("Starting the delete API");
        Product p = productService.deleteProduct(id);
        System.out.println("Ending the delete API");
        return p;
    }
}
