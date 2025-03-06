package com.scaler.backendproject.service;

import com.scaler.backendproject.dto.FakeStoreProductDTO;
import com.scaler.backendproject.exceptions.CategoryNotFoundException;
import com.scaler.backendproject.models.Category;
import com.scaler.backendproject.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service("fakeStoreCategoryService")
public class FakeStoreCategoryService implements CategoryService {

    private RestTemplate restTemplate;

    public FakeStoreCategoryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Category createCategory(String categoryTitle) {
        //In FakeStore there is no API hit for createCategory
//        FakeStoreCategoryDTO fakeStoreCategoryDTO = new FakeStoreCategoryDTO();
//        fakeStoreCategoryDTO.setId(id);
//        fakeStoreCategoryDTO.setCategoryTitle(categoryTitle);
//
//        fakeStoreCategoryDTO
        return null;
    }

    public List<Product> getProductsInCategory(String category) throws NoSuchElementException {

        FakeStoreProductDTO[] fakeStoreListOfProducts = null;
        try {
            //Fetch products for the given category from the API
            fakeStoreListOfProducts =
                    restTemplate.getForObject(
                            "https://fakestoreapi.com/products/category/" + category,
                            FakeStoreProductDTO[].class
                    );
            if (fakeStoreListOfProducts == null || fakeStoreListOfProducts.length == 0) {
                throw new CategoryNotFoundException("No products found for category " + category);
            }
        } catch (Exception e) {
            throw new NoSuchElementException("Error fetching products for category " + category, e);
        }

        return new FakeStoreProductDTO().getListOfProducts(fakeStoreListOfProducts);
    }

    public List<Category> getAllCategories() throws NullPointerException {
        String[] fakeStoreListOfCategories =
                restTemplate.getForObject("https://fakestoreapi.com/products/categories",
                        String[].class);

        if (fakeStoreListOfCategories == null) {
            throw new NullPointerException("No Categories found");
        }

        //Change the Response Type:
        //Modified the RestTemplate.getForObject call to expect String[].class
        //instead of FakeStoreCategoryDTO[].class, which matches the API response.
        //Then we can convert the string array (["electronics", "jewelery", ...])
        //into a list of Category objects using Java Streams.

        //return new FakeStoreCategoryDTO().getListOfCategories(fakeStoreListOfCategories);
        // Convert the array of strings to a list of Category objects

        List<Category> listOfCategories = new ArrayList<>();

        for (String categoryTitle : fakeStoreListOfCategories) {
            Category category = new Category();
            category.setTitle(categoryTitle);
            listOfCategories.add(category);
        }

        return listOfCategories;
//
//        return Arrays.stream(fakeStoreListOfCategories)
//                .map(categoryTitle -> {
//                    Category category = new Category();
//                    category.setTitle(categoryTitle);
//                    return category;
//                })
//                .toList();
    }
}
