package com.example.demokoro.service;

import com.example.demokoro.models.OrderDetail;
import com.example.demokoro.repository.OrderDetailRepository;
import com.example.demokoro.serviceImpl.IOrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailService implements IOrderDetailRepository {
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }
}
