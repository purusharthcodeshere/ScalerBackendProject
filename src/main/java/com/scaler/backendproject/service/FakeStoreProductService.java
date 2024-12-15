package com.scaler.backendproject.service;

import com.scaler.backendproject.dto.FakeStoreProductDTO;
import com.scaler.backendproject.models.Category;
import com.scaler.backendproject.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//This annotation here is going to tell SpringBoot that this is one of our important class so create an object of this
@Service
public class FakeStoreProductService implements ProductService {

    //Inside this, fake store is going to be third party service

    @Autowired
    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Product getSingleProduct(Long id) {
        System.out.println("We are inside the single product in FakeStoreProductService");
        FakeStoreProductDTO fakeStoreProductDTO =
                restTemplate.getForObject("https://fakestoreapi.com/products/" + id,
                FakeStoreProductDTO.class);

//        System.out.println(fakeStoreProductDTO.toString());
        return fakeStoreProductDTO.getProduct();
    }

//    @Override
//    public Product[] getAllProducts(FakeStoreProductDTO[] listOfProducts) {
//        return new Product[0];
//    }

    public List<Product> getAllProducts() {
        System.out.println("In the getAllProducts API in FKSPS");
        FakeStoreProductDTO[] fakeStoreListOfProducts =
                restTemplate.getForObject("https://fakestoreapi.com/products/",
                        FakeStoreProductDTO[].class);
        return new FakeStoreProductDTO().getListOfProducts(fakeStoreListOfProducts);
    }

    @Override
    public Product createProduct(Long id, String title, String description,
                                 Double price, String category, String imageUrl) {
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setId(id);
        fakeStoreProductDTO.setTitle(title);
        fakeStoreProductDTO.setDescription(description);
        fakeStoreProductDTO.setPrice(price);
        fakeStoreProductDTO.setCategory(category);
        //Added this as Homework
        //So we had to change the FakeStoreProductService class
        //And since FakeStoreProductService implements ProductService Interface
        //We had to change that method signature as well
        fakeStoreProductDTO.setImage(imageUrl);

        FakeStoreProductDTO response = restTemplate.postForObject("https://fakestoreapi.com/products",
                fakeStoreProductDTO, FakeStoreProductDTO.class);

        return response.getProduct();
    }

    @Override
    public Product deleteProduct(Long id) {
        System.out.println("Inside the delete product in FakeStoreProductService API");
        //No return type, as the restTemplate returns void for delete function
        //So, we first store the product in a temp variable
        //To show which product we are deleting
        Product deletedProduct = getSingleProduct(id);
        restTemplate.delete("https://fakestoreapi.com/products/" + id);
        return deletedProduct;
    }

    public Product updateProduct(Long id, String title, String description,
                                 Double price, Category category, String imageUrl) {
        System.out.println("Inside the update product in FakeStoreProductService API");
        Product existingProduct = getSingleProduct(id);

        if (existingProduct != null) {
            FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
            System.out.println("Updating the Product");
            fakeStoreProductDTO.setId(id);
            if (title != null) {
                existingProduct.setTitle(title);
            }
            if (description != null) {
                existingProduct.setDescription(description);
            }
            if (price != null) {
                existingProduct.setPrice(price);
            }
            if (category != null) {
                existingProduct.setCategory(category);
            }
            if (imageUrl != null) {
                existingProduct.setImageUrl(imageUrl);
            }
            Product response =
                    restTemplate.patchForObject("https://fakestoreapi.com/products/" + id,
                            existingProduct, Product.class);
            return response;
        } else {
            throw new RuntimeException("Product Not Found");
        }
//        return null;
    }

}
