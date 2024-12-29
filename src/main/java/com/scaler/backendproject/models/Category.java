package com.scaler.backendproject.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Category extends BaseModel {
    private String title;

    //We declare the cardinality between the Product and the Category Class
    //We mention mappedBy here so that Hibernate
    // does not create another mapping on its own
    //Since in the Product class, this cardinality is mapped by the category attribute,
    //we mention that here to indicate Hibernate not to create another mapping
    //We also mention the fetch type b/w Eager and Lazy
//    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
//    private List<Product> products;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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
