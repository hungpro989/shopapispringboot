package com.example.demokoro.serviceImpl;

import com.example.demokoro.dto.OrderCreateDTO;
import com.example.demokoro.dto.OrderDTO;
import com.example.demokoro.dto.OrderPrintMultipleDTO;

import java.util.List;

public interface IOrderService {
    List<OrderDTO> getAll();
//    List<OrderDTO> getAllByCondition(Integer employeeId,Integer creatorId, Integer businessId, Integer deliveryId, Integer orderStatusId, Integer orderTypeId,String start, String end);
    List<OrderDTO> getAllByCondition(Integer employeeId,Integer creatorId, Integer businessId, Integer deliveryId, Integer orderStatusId, Integer orderTypeId, String orderTimeStart, String orderTimeEnd);
    List<OrderDTO> getAllByBusinessId(Integer id);
    OrderDTO getById(Integer id);
    boolean deleteById(Integer id);
    boolean save(OrderCreateDTO order);
    boolean updateStatus(Integer id, Integer statusId);
    boolean updateDelivery(Integer id, Integer deliveryId);
    List<OrderDTO> getOrderByStatus(Integer id);
    OrderDTO printBill(Integer id, Integer deliveryId);
    List<OrderDTO> printMultipleBill(OrderPrintMultipleDTO orderPrintMultipleDTO);
    OrderDTO getOrderByBillCode(String billCode);
    List<OrderDTO> findOrderByPhoneOrBillCode(String phone, String billCode);
}
