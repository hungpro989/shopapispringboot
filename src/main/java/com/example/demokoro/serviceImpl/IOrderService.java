package com.example.demokoro.serviceImpl;

import com.example.demokoro.dto.OrderCreateDTO;
import com.example.demokoro.dto.OrderDTO;
import com.example.demokoro.models.Order;

import java.util.List;

public interface IOrderService {
    List<OrderDTO> getAll();
//    List<OrderDTO> getAllByCondition(Integer employeeId,Integer creatorId, Integer businessId, Integer deliveryId, Integer orderStatusId, Integer orderTypeId,String start, String end);
    List<OrderDTO> getAllByCondition(Integer employeeId,Integer creatorId, Integer businessId, Integer deliveryId, Integer orderStatusId, Integer orderTypeId, String orderTimeStart, String orderTimeEnd);
    List<OrderDTO> getAllByBusinessId(Integer id);
    OrderDTO getById(Integer id);
    boolean deleteById(Integer id);
    boolean save(OrderCreateDTO order);
}
