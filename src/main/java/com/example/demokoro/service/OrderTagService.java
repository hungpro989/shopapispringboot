package com.example.demokoro.service;

import com.example.demokoro.models.CategoryProduct;
import com.example.demokoro.models.OrderTag;
import com.example.demokoro.repository.CategoryProductRepository;
import com.example.demokoro.repository.OrderTagRepository;
import com.example.demokoro.serviceImpl.ICategoryProductService;
import com.example.demokoro.serviceImpl.IOrderTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderTagService implements IOrderTagService {
    @Autowired
    OrderTagRepository orderTagRepository;
    @Override
    public void save(OrderTag ot) {
        orderTagRepository.save(ot);
    }
    @Override
    public List<OrderTag> findOrderTagByTagId(Integer id) {
        return orderTagRepository.findOrderTagByTagId(id);
    }
}
