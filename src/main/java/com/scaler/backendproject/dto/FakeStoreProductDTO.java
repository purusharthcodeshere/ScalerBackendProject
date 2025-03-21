package com.scaler.backendproject.dto;

import com.scaler.backendproject.models.Category;
import com.scaler.backendproject.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FakeStoreProductDTO {
    private Long id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;

    //Used getters and setters because lombok was not working
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

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

    public List<Product> getListOfProducts(FakeStoreProductDTO[] fakeStoreListOfProducts) {
        List<Product> listOfProducts = new ArrayList<>();
        for (FakeStoreProductDTO fakeStoreListOfProduct : fakeStoreListOfProducts) {
            listOfProducts.add(fakeStoreListOfProduct.getProduct());
        }

        return listOfProducts;
    }

    @Override
    public String toString() {
        return "FakeStoreProductDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", image='" + image + '\'' +
                '}';
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