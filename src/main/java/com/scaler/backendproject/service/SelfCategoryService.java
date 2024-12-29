package com.scaler.backendproject.service;

import com.scaler.backendproject.models.Category;
import com.scaler.backendproject.models.Product;
import com.scaler.backendproject.repository.CategoryRepository;
import com.scaler.backendproject.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service("selfCategoryService")
public class SelfCategoryService implements CategoryService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SelfCategoryService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category createCategory(String categoryTitle) {
        Category category = new Category();
        category.setTitle(categoryTitle);

        //Saving the Category in the repository as well as returning it in the same command.
        return categoryRepository.save(category);
    }

    @Override
    public List<Product> getProductsInCategory(String categoryTitle) throws NoSuchElementException {
        Optional<Category> category;
        Optional<List<Product>> productsInCategory;
        try {
            category = categoryRepository.findByTitle(categoryTitle);
            productsInCategory = Optional.of(productRepository.getProductByCategoryId(category.get().getId()));
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("No products found under " + categoryTitle);
        }
        return productsInCategory.get();
    }

    @Override
    public List<Category> getAllCategories() throws NullPointerException {
        Optional<List<Category>> listOfCategories = Optional.of(categoryRepository.findAll());
        if (listOfCategories.isEmpty()) {
            throw new NullPointerException("No Categories in the database");
        }
        return listOfCategories.get();
    }
}
