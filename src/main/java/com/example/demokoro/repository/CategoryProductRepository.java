package com.example.demokoro.repository;

import com.example.demokoro.models.CategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface CategoryProductRepository extends JpaRepository<CategoryProduct, Integer> {
    //CategoryProduct deleteByProductId(Integer id);
    //@Modifying
    //@Transactional
//    @Query("delete from CategoryProduct c where c.product.id = :product_id")
//    void deleteProductId(@Param("product_id") Integer product_id);
    @Transactional
    void deleteByProductId(Integer productId);
}
