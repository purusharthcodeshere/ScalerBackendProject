package com.scaler.backendproject.service;

import com.scaler.backendproject.exceptions.ProductNotFoundException;
import com.scaler.backendproject.models.Category;
import com.scaler.backendproject.models.Product;
import com.scaler.backendproject.repository.CategoryRepository;
import com.scaler.backendproject.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            return product.get();
        }

        throw new ProductNotFoundException("Product not found in our database");
    }

//    @Override
//    public List<Product> getAllProducts() throws ProductNotFoundException {
//        Optional<List<Product>> listOfProducts = Optional.of(productRepository.findAll());
//        if (listOfProducts.isEmpty()) {
//            throw new ProductNotFoundException("No Products in the database");
//        }
//        return listOfProducts.get();
//    }

    //Changed the signature to include pagination and searching
    //Thus had to change all the method signatures as well
    @Override
    public Page<Product> getAllProducts(int pageNumber, int pageSize, String fieldName) throws ProductNotFoundException {
        Page<Product> listOfProducts = productRepository.findAll(
                PageRequest.of(
                        pageNumber,
                        pageSize,
                        Sort.by(fieldName).ascending()
                )
        );

        if (!listOfProducts.hasContent()) {
            throw new ProductNotFoundException("Products not found in our database");
        }

        return listOfProducts;
    }

    @Override
    public Product createProduct(Long id, String title, String description, Double price, String categoryTitle, String imageUrl) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(imageUrl);

        //We have to first check if the given category is present or not
        //If it is not present, then we have to create new Category
        //And save it in the category repository.
        //We use Optional Class because if the category is not present
        //Then it will return null instead of throwing an error
        Optional<Category> currentCategory = categoryRepository.findByTitle(categoryTitle);

        if (currentCategory.isEmpty()) {
            //This means category is not present in our database
            Category newCategory = new Category();
            newCategory.setTitle(categoryTitle);
            Category newRow = categoryRepository.save(newCategory);
            product.setCategory(newRow);
        } else {
            product.setCategory(currentCategory.get());
        }

        //Saving the product in the repository as well as returning it at the same time.
        return productRepository.save(product);
    }

    @Override
    public Product deleteProduct(Long id) throws ProductNotFoundException {
        Product deletedProduct = getSingleProduct(id);
        try {
            deletedProduct.setDeleted(true);
            productRepository.delete(deletedProduct);
        } catch (Exception e) {
            throw new ProductNotFoundException("Product not found in our database");
        }
        return deletedProduct;
    }

    @Override
    public Product updateProduct(Long id, String title, String description, Double price, Category category, String imageUrl) throws ProductNotFoundException {
        // Fetch the product by ID
        Product existingProduct = getSingleProduct(id);

        // Update fields if the new values are not null (allowing partial updates)
        if (title != null && !title.isEmpty()) {
            existingProduct.setTitle(title);
        }
        if (description != null && !description.isEmpty()) {
            existingProduct.setDescription(description);
        }
        if (price != null) {
            existingProduct.setPrice(price);
        }
        if (imageUrl != null && !imageUrl.isEmpty()) {
            existingProduct.setImageUrl(imageUrl);
        }

        // Update the category if provided
        if (category != null) {
            // Check if the category already exists in the database
            Optional<Category> existingCategory = categoryRepository.findByTitle(category.getTitle());
            if (existingCategory.isEmpty()) {
                // Save the new category
                Category newCategory = categoryRepository.save(category);
                existingProduct.setCategory(newCategory);
            } else {
                existingProduct.setCategory(existingCategory.get());
            }
        }

        // Save and return the updated product
        return productRepository.save(existingProduct);
    }
}
