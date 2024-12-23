package com.scaler.backendproject.repository;

import com.scaler.backendproject.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    //Hibernate will create a query for us automatically, behind the scenes
    //And we do not have to write a query like below:

    //This will insert product records in my product table
    Product save(Product product);


    //  SELECT *
    //  FROM
    //      Product
    //  WHERE
    //      description = xyz (passed argument)
    Product findByDescription(String description);

    //  SELECT *
    //  FROM
    //      Product
    //  WHERE
    //      title = xyz (passed argument)
    Product findByTitle(String title);

    //  SELECT *
    //  FROM
    //      Product
    //  WHERE
    //      description = xyz AND
    //      title = abc             (passed arguments)
    Product findByTitleAndDescription(String title, String description);
}
