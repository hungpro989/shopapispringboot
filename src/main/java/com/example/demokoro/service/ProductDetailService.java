package com.example.demokoro.service;

import com.example.demokoro.models.ProductDetail;
import com.example.demokoro.dto.ProductDetailDTO;
import com.example.demokoro.repository.ProductDetailRepository;
import com.example.demokoro.serviceImpl.IProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductDetailService implements IProductDetailService
{
    @Autowired
    ProductDetailRepository productDetailRepository;
    @Override
    public ProductDetail save(ProductDetail p) {
        return productDetailRepository.save(p);
    }

    @Override
    public String copyProductDetail(Integer id) {
        ProductDetail pd = productDetailRepository.findById(id).orElse(null);
        ProductDetail p = new ProductDetail(pd);
        p.setId(null);
        p.setCodeName(p.getCodeName()+"_copy");
        if(save(p)!=null){
            return "true";
        }
        return "false";
    }

    @Override
    public List<ProductDetailDTO> getAllProductDetail() {
        List<ProductDetailDTO> listDto = new ArrayList<>();
        List<ProductDetail> list = productDetailRepository.findAll();
        for (ProductDetail productDetail : list) {
            listDto.add(new ProductDetailDTO(productDetail));
        }
        return listDto;
    }

}
