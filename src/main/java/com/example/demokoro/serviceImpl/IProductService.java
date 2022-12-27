package com.example.demokoro.serviceImpl;

import com.example.demokoro.dto.ProductDTOAdmin;
import com.example.demokoro.models.Product;

import java.util.List;

public interface IProductService {
    List<ProductDTOAdmin> getAllProduct();
    ProductDTOAdmin getProducById(Integer id);
    void deleteById(Integer id);
    Product save(Product product);
    boolean checkExistName(String name);
    boolean checkExistId(Integer id);
}
