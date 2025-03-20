package com.scaler.backendproject.repository;

import com.scaler.backendproject.models.Product;
import com.scaler.backendproject.repository.projections.ProductProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    //Hibernate will create a query for us automatically, behind the scenes
    //And we do not have to write a query like below:

    //This will insert product records in my product table
    Product save(Product product);

    //This will return the list of all products
    Page<Product> findAll(Pageable pageable);

    //SELECT * FROM Product WHERE description = xyz (passed argument)
    Product findByDescription(String description);

    //SELECT * FROM Product WHERE title = xyz (passed argument)
    Product findByTitle(String title);

    //SELECT * FROM Product WHERE description = xyz AND title = abc (passed arguments)
    Product findByTitleAndDescription(String title, String description);

    //Implement HQL Query
    @Query("SELECT p FROM Product AS p WHERE p.category.id =:categoryId")
    List<Product> getProductByCategoryId(@Param("categoryId") Long categoryId);

    //Implementing Native Query (SQL)
    @Query(value = "SELECT * FROM Product AS p WHERE p.category_id =:categoryId", nativeQuery = true)
    List<Product> getProductByCategoryIdByNativeQuery(@Param("categoryId") Long categoryId);

    //Implement Product Projection
    @Query("SELECT p.title AS title, p.id AS id FROM Product AS p WHERE p.category.id =:categoryId")
    List<ProductProjection> getProductByCategoryIdUsingProjections(@Param("categoryId") Long categoryId);
}
