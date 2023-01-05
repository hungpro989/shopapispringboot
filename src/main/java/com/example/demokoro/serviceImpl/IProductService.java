package com.example.demokoro.serviceImpl;

import com.example.demokoro.dto.ProductCreateDTO;
import com.example.demokoro.dto.ProductDTOAdmin;
import com.example.demokoro.models.Product;

import java.util.List;

public interface IProductService {
    List<ProductDTOAdmin> getAllProduct();
    ProductDTOAdmin getProducById(Integer id);
    void deleteById(Integer id);
    boolean save(Product product);
    Product checkExistName(String name);
    boolean checkExistId(Integer id);
    //String create(ProductCreateDTO productCreateDTO, Integer id);

    String updateAndCheckProduct(ProductCreateDTO productCreateDTO, Integer id);
    String copyProduct(Integer id);
}
