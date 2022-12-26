package com.example.demokoro.service;

import com.example.demokoro.models.ProductDetail;
import com.example.demokoro.repository.ProductDetailRepository;
import com.example.demokoro.serviceImpl.IProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailService implements IProductDetailService {
    @Autowired
    ProductDetailRepository productDetailRepository;
    @Override
    public ProductDetail save(ProductDetail p) {
        return productDetailRepository.save(p);
    }
}
