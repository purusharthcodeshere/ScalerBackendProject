package com.scaler.backendproject.dto;

import com.scaler.backendproject.models.Category;
import com.scaler.backendproject.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDTO {
    private Long id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;

    //This will get the product of my implementation
    //using the values from Fake Store
    public Product getProduct() {
        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setPrice(price);
        product.setDescription(description);
        product.setImageUrl(image);

        //Used cat, as "Category" was already used as attribute
        Category cat = new Category();
        cat.setTitle(category);
        product.setCategory(cat);

        return product;
    }
}




//{
//        "id": 1,
//        "title": "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
//        "price": 109.95,
//        "description": "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
//        "category": "men's clothing",
//        "image": "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
//        "rating": {
//        "rate": 3.9,
//        "count": 120
//        }
//        }