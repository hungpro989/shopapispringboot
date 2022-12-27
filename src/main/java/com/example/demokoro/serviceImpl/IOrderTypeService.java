

package com.example.demokoro.serviceImpl;

import com.example.demokoro.dto.OrderTypeDTO;
import com.example.demokoro.models.OrderType;

import java.util.List;

public interface IOrderTypeService {
    List<OrderTypeDTO> getAll();
    OrderTypeDTO getById(Integer id);
    boolean deleteById(Integer id);
    boolean save(OrderType delivery);
    boolean checkExistName(String name);
    boolean checkExistId(Integer id);
}
