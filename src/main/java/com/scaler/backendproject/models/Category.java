package com.scaler.backendproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Category extends BaseModel {
    @Setter
    @Getter
    private String title;

    //We declare the cardinality between the Product and the Category Class
    //We mention mappedBy here so that Hibernate
    // does not create another mapping on its own
    //Since in the Product class, this cardinality is mapped by the category attribute,
    //we mention that here to indicate Hibernate not to create another mapping
    //We also mention the fetch type b/w Eager and Lazy

    @JsonIgnore
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products;

    //    public List<Product> getProducts() {
//        return products;
//    }
//
//    public void setProducts(List<Product> products) {
//        this.products = products;
//    }

    @Override
    public String toString() {
        return "Category{" +
                "title='" + title + '\'' +
                '}';
    }
}
