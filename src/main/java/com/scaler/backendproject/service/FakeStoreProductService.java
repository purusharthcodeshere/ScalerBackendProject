package com.scaler.backendproject.service;

import com.scaler.backendproject.dto.FakeStoreProductDTO;
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

    public Product getSingleProduct(long id) {
        System.out.println("We are inside the single product in FakeStoreProductService");
        FakeStoreProductDTO fakeStoreProductDTO =
                restTemplate.getForObject("https://fakestoreapi.com/products/" + id,
                FakeStoreProductDTO.class);

        System.out.println(fakeStoreProductDTO.toString());
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
    public Product deleteProduct(long id) {
        System.out.println("Inside the delete product in FakeStoreProductService API");
        //No return type, as the restTemplate returns void for delete function
        restTemplate.delete("https://fakestoreapi.com/products/" + id);
        return getSingleProduct(id);
    }

    public Product updateProduct(long id, String title, String description, Double price, String category, String imageUrl) {

        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setTitle(title);
        fakeStoreProductDTO.setDescription(description);
        fakeStoreProductDTO.setPrice(price);
        fakeStoreProductDTO.setCategory(category);
        return null;
    }

}
