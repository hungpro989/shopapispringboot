package com.example.demokoro.repository;

import com.example.demokoro.models.CategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryProductRepository extends JpaRepository<CategoryProduct, Integer> {
    void deleteCategoryProductByProductId(Integer productId);

    List<CategoryProduct> findCategoryProductByProductId(Integer id);
}
