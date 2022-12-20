package com.example.demokoro.repository;

import com.example.demokoro.dto.CategoryDTOAdmin;
import com.example.demokoro.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findCategoryByName(String name);
}
