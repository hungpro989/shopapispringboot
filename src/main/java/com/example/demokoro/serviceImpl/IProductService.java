package com.example.demokoro.serviceImpl;

import com.example.demokoro.dto.CategoryDTOAdmin;
import com.example.demokoro.dto.ProductDTOAdmin;
import com.example.demokoro.models.Category;
import com.example.demokoro.models.Product;

import java.util.List;

public interface IProductService {
    List<ProductDTOAdmin> getAllProduct();
    ProductDTOAdmin getProducById(Integer id);
    boolean deleteById(Integer id);
    boolean save(Product category);
    boolean checkExistName(String name);
    boolean checkExistId(Integer id);
}
