package com.scaler.backendproject.controller;

import com.scaler.backendproject.dto.ErrorDTO;
import com.scaler.backendproject.exceptions.ProductNotFoundException;
import com.scaler.backendproject.models.Product;
import com.scaler.backendproject.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    //This product controller has now the dependency on the productService
    private final ProductService productService;

    //Constructor to pass service to pass in controller so we can implement it
    //Added Qualifier Annotation to differentiate between which class we want to choose
    //FakeStoreProductService or selfProductService

    //Implemented Redis for FakeStoreProductService
    //So had changed the Qualifier here
    //To check redis functionality,
    //Change the Qualifier to "fakeStoreProductService"
    //Also, have to implement, REDIS for selfProductService
    public ProductController(@Qualifier("selfProductService") ProductService productService) {
        this.productService = productService;
    }

    //This will help in performing "Create" function
    //@RequestMapping(value = "/product", method = RequestMethod.POST)
    //Post mapping does the same thing as request mapping of post and is a shortcut
    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        //Creating new object of Product to pass the object in response entity
        Product newCreatedProduct = productService.createProduct(product.getId(),
                product.getTitle(), product.getDescription(),
                product.getPrice(), product.getCategory().getTitle(),
                product.getImageUrl());

        //Local variable 'productResponseEntity' is redundant
        return new ResponseEntity<>(
                newCreatedProduct,
                HttpStatus.CREATED
        );
    }

    //This will help in "Retrieve" function
    //@RequestMapping(value = "/product", method = RequestMethod.GET)
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        System.out.println("Starting the getSingleProduct API here");
        Product p = productService.getSingleProduct(id);
        System.out.println("Ending the API here");

        //First argument is the data we want to pass
        //Next is the HttpStatus Class code
        //Local variable 'productResponseEntity' is redundant

        return new ResponseEntity<>(
                p,              //First argument is the data we want to pass
                HttpStatus.OK   //Next is the HttpStatus Class code
        );
    }

//    @GetMapping("/products")
//    public ResponseEntity<List<Product>> getAllProducts() {
//        System.out.println("Starting the Get all Products API here");
//        List<Product> productList = null;
//        try {
//            productList = productService.getAllProducts();
//        } catch (ProductNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("Ending the Get All products API");
//
//        //Local variable 'productResponseEntity' is redundant
//        //Added response entity for all products as well
//        return new ResponseEntity<>(
//                productList,
//                HttpStatus.OK
//        );
//    }
//

    //Added getAllProducts Using pagination and sorting
    //That's why commented the previous getRequestMapping above
    @GetMapping("/products")
    public ResponseEntity<Page<Product>> getAllProducts(
            @RequestParam("pageNumber") int pageNumber,
            @RequestParam("pageSize") int pageSize,
            @RequestParam("fieldName") String fieldName
    ) {
        Page<Product> productList = null;
        try {
            productList = productService.getAllProducts(pageNumber, pageSize, fieldName);
        } catch (ProductNotFoundException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(
                productList,
                HttpStatus.OK
        );
    }

    //This will help in "Update" function
    //@RequestMapping(value = "/product", method = RequestMethod.PUT)
    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) throws ProductNotFoundException {
        Product updatedProduct = productService.updateProduct(id,
                product.getTitle(), product.getDescription(),
                product.getPrice(), product.getCategory(),
                product.getImageUrl());

        //Local variable 'updatedProductResponseEntity' was redundant
        return new ResponseEntity<>(
                updatedProduct,
                HttpStatus.OK
        );
    }

    //This will help in "Delete" function
    //@RequestMapping(value = "/product", method = RequestMethod.DELETE)
    @DeleteMapping("/product/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
        System.out.println("Starting the delete API");
        Product deletedProduct = productService.deleteProduct(id);
        System.out.println("Ending the delete API");

        //Local variable 'deleteResponseEntity' is redundant

        return new ResponseEntity<>(
                deletedProduct,
                HttpStatus.ACCEPTED
                //The HTTP status code 202 "Accepted"
                //indicates that a request has been accepted for processing,
                //but the processing has not been completed
        );
    }

    //This method will be invoked whenever there will be a
    //ProductNotFoundException in the call trace
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleProductNotFoundException(Exception e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(e.getMessage());

        //Local variable 'errorResponseEntity' is redundant
        return new ResponseEntity<>(
                errorDTO,
                HttpStatus.NOT_FOUND
        );
    }
}
