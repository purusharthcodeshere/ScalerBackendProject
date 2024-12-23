package com.scaler.backendproject.repository;

import com.scaler.backendproject.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findById(long id);
    Category findByTitle(String title);
}
