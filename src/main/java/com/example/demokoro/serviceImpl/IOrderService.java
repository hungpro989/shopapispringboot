package com.example.demokoro.serviceImpl;

import com.example.demokoro.dto.OrderDTO;
import com.example.demokoro.models.Order;

import java.util.List;

public interface IOrderService {
    List<OrderDTO> getAll();
    OrderDTO getById(Integer id);
    boolean deleteById(Integer id);
    boolean save(Order entity);
}
