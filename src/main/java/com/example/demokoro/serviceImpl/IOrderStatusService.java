

package com.example.demokoro.serviceImpl;

import com.example.demokoro.dto.DeliveryDTO;
import com.example.demokoro.dto.OrderStatusDTO;
import com.example.demokoro.models.Delivery;
import com.example.demokoro.models.OrderStatus;

import java.util.List;

public interface IOrderStatusService {
    List<OrderStatusDTO> getAll();
    OrderStatusDTO getById(Integer id);
    boolean deleteById(Integer id);
    boolean save(OrderStatus delivery);
    boolean checkExistName(String name);
    boolean checkExistId(Integer id);
}
