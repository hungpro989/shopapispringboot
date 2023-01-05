package com.example.demokoro.service;

import com.example.demokoro.models.CategoryProduct;
import com.example.demokoro.repository.CategoryProductRepository;
import com.example.demokoro.serviceImpl.ICategoryProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryProductService implements ICategoryProductService {
    @Autowired
    CategoryProductRepository categoryProductRepository;
    @Override
    public void save(CategoryProduct cp) {
        categoryProductRepository.save(cp);
    }

    @Override
    public void deleteById(Integer id) {
        categoryProductRepository.deleteCategoryProductByProductId(id);
    }

    @Override
    public List<CategoryProduct> findCategoryProductByProductId(Integer id) {

        return categoryProductRepository.findCategoryProductByProductId(id);
    }


}
