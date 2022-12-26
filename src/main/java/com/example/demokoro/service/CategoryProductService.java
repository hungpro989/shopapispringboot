package com.example.demokoro.service;

import com.example.demokoro.models.CategoryProduct;
import com.example.demokoro.repository.CategoryProductRepository;
import com.example.demokoro.serviceImpl.ICategoryProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CategoryProductService implements ICategoryProductService {
    @Autowired
    CategoryProductRepository categoryProductRepository;
    @Override
    public CategoryProduct save(CategoryProduct cp) {
        return categoryProductRepository.save(cp);
    }

    @Override
    public void deleteById(Integer product_id) {
        categoryProductRepository.deleteByProductId(product_id);
    }


}
