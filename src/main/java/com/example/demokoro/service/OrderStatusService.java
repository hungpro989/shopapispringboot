package com.example.demokoro.service;

import com.example.demokoro.dto.OrderStatusDTO;
import com.example.demokoro.models.OrderStatus;
import com.example.demokoro.repository.OrderStatusRepository;
import com.example.demokoro.serviceImpl.IOrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderStatusService implements IOrderStatusService {
    @Autowired
    OrderStatusRepository orderStatusRepository;
    @Override
    public List<OrderStatusDTO> getAll() {
        List<OrderStatusDTO> listDto = new ArrayList<>();
        List<OrderStatus> list = orderStatusRepository.findAll();
        for(OrderStatus var: list){
            listDto.add((new OrderStatusDTO(var)));
        }
        return listDto;
    }

    @Override
    public OrderStatusDTO getById(Integer id) {
        OrderStatusDTO dto = new OrderStatusDTO();
        if(orderStatusRepository.findById(id).isPresent()){
            OrderStatus orderStatus = orderStatusRepository.findById(id).get();
            dto = new OrderStatusDTO(orderStatus);
            return dto;
        }
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        try{
            orderStatusRepository.deleteById(id);
            return true;
        }catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean save(OrderStatus orderStatus) {
        try{
            orderStatusRepository.save(orderStatus);
            return  true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean checkExistName(String name) {
        if (orderStatusRepository.findOrderStatusByName(name)!=null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkExistId(Integer id) {
        if(orderStatusRepository.findById(id).orElse(null)!=null){
            return true;
        }
        return false;
    }
}
