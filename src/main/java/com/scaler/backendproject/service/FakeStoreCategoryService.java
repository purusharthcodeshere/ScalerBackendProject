package com.scaler.backendproject.service;

import com.scaler.backendproject.dto.FakeStoreCategoryDTO;
import com.scaler.backendproject.exceptions.CategoryNotFoundException;
import com.scaler.backendproject.models.Category;
import com.scaler.backendproject.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("fakeStoreCategoryService")
public class FakeStoreCategoryService implements CategoryService {

    private RestTemplate restTemplate;

    public FakeStoreCategoryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Category createCategory(Long id, String categoryTitle) {
        //In FakeStore there is no API hit for createCategory
//        FakeStoreCategoryDTO fakeStoreCategoryDTO = new FakeStoreCategoryDTO();
//        fakeStoreCategoryDTO.setId(id);
//        fakeStoreCategoryDTO.setCategoryTitle(categoryTitle);
//
//        fakeStoreCategoryDTO
        return null;
    }

    public List<Product> getProductsInCategory(String category) throws CategoryNotFoundException {
        return List.of();
    }

    public List<Category> getAllCategories() throws NullPointerException {
        FakeStoreCategoryDTO[] fakeStoreListOfCategories =
                restTemplate.getForObject("https://fakestoreapi.com/products/categories",
                        FakeStoreCategoryDTO[].class);

        if (fakeStoreListOfCategories == null) {
            throw new NullPointerException("No Categories found");
        }
        return new FakeStoreCategoryDTO().getListOfCategories(fakeStoreListOfCategories);
    }
}
