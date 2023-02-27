package com.example.demokoro.serviceImpl;

import com.example.demokoro.models.ProductDetail;
import com.example.demokoro.dto.ProductDetailDTO;

import java.util.List;

public interface IProductDetailService {
    ProductDetail save(ProductDetail productDetail);
    String copyProductDetail(Integer id);
    List<ProductDetailDTO> getAllProductDetail();
}
