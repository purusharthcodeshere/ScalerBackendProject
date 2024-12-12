package com.scaler.backendproject.service;

import com.scaler.backendproject.dto.FakeStoreProductDTO;
import com.scaler.backendproject.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

//This annotation here is going to tell SpringBoot that this is one of our important class so create an object of this
@Service
public class FakeStoreProductService implements ProductService {

    //Inside this, fake store is going to be third party service

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


    public List<Product> getAllProducts() {
        return List.of();
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

}
