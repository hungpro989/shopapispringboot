package com.example.demokoro.serviceImpl;

import com.example.demokoro.models.CategoryProduct;

public interface ICategoryProductService {
    CategoryProduct save(CategoryProduct cp);
    void deleteById(Integer id);
}
