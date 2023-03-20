package com.example.demokoro.serviceImpl;

import com.example.demokoro.dto.OrderDeliveryDTO;
import com.example.demokoro.models.OrderDelivery;

import java.util.List;

public interface IOrderDeliveryRepository {
    List<OrderDelivery> getAll();
    OrderDeliveryDTO getById(Integer id);
    boolean deleteById(Integer id);
    boolean save(OrderDeliveryDTO dto);
    boolean checkExistCodeDelivery(String s);
    OrderDeliveryDTO getByOrderId(Integer id);
}
