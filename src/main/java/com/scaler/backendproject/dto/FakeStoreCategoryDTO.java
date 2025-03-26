package com.scaler.backendproject.dto;

import com.scaler.backendproject.models.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FakeStoreCategoryDTO {
    private Long id;
    private String title;

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

    public Category getCategory() {
        Category category = new Category();
        category.setId(id);
        category.setTitle(title);

        return category;
    }

    public List<Category> getListOfCategories(FakeStoreCategoryDTO[] fakeStoreCategoryDTO) {
        List<Category> categories = new ArrayList<>();
        for (FakeStoreCategoryDTO categoryDTO : fakeStoreCategoryDTO) {
            categories.add(categoryDTO.getCategory());
        }

        return categories;
    }
}
