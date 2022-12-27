package com.example.demokoro.service;

import com.example.demokoro.dto.ProductDTOAdmin;
import com.example.demokoro.models.Product;
import com.example.demokoro.repository.ProductRepository;
import com.example.demokoro.serviceImpl.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductService implements IProductService {
    @Autowired
    ProductRepository productRepository;
    @Override
    public List<ProductDTOAdmin> getAllProduct() {
        List<ProductDTOAdmin> listDto = new ArrayList<>();
        List<Product> list = productRepository.findAll();
        for (Product product : list) {
            listDto.add(new ProductDTOAdmin(product));
        }
        return listDto;
    }

    @Override
    public ProductDTOAdmin getProducById(Integer id) {
        ProductDTOAdmin dto = new ProductDTOAdmin();
        if(productRepository.findById(id).isPresent()){
            Product p = productRepository.findById(id).get();
            dto = new ProductDTOAdmin(p);
            return dto;
        }
        return dto;
    }

    @Override
    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public boolean checkExistName(String name) {
        Product p = productRepository.findProductByName(name);
        if(p==null){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkExistId(Integer id) {
        Product p = productRepository.findById(id).orElse(null);
        if(p!=null){
            //tồn tại trong db
            return true;
        }
        //ko tồn tại trong db
        return false;
    }
}
