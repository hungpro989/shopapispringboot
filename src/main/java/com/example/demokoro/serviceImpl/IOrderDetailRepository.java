package com.example.demokoro.serviceImpl;


import com.example.demokoro.models.OrderDetail;

public interface IOrderDetailRepository {
    OrderDetail save(OrderDetail orderDetail);
}
