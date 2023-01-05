package com.example.demokoro.serviceImpl;

import com.example.demokoro.models.CategoryProduct;

import java.util.List;

public interface ICategoryProductService {
    void save(CategoryProduct cp);
    void deleteById(Integer id);
    List<CategoryProduct> findCategoryProductByProductId(Integer id);
}
