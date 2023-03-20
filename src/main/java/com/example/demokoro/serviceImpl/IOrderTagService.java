package com.example.demokoro.serviceImpl;

import com.example.demokoro.models.OrderTag;

import java.util.List;

public interface IOrderTagService {
    void save(OrderTag cp);
    List<OrderTag> findOrderTagByTagId(Integer id);
    boolean deleteOrderTagByOrderId (Integer orderId);
}
