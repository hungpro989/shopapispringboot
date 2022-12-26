package com.example.demokoro.repository;

import com.example.demokoro.models.CategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryProductRepository extends JpaRepository<CategoryProduct, Integer> {
}
