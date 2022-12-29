package com.example.demokoro.service;

import com.example.demokoro.dto.OrderDTO;
import com.example.demokoro.models.Order;
import com.example.demokoro.repository.OrderRepository;
import com.example.demokoro.serviceImpl.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class OrderService implements IOrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<OrderDTO> getAll() {
        List<OrderDTO> listDto = new ArrayList<>();
        List<Order> list = orderRepository.findAll();
        for(Order var: list){
            listDto.add((new OrderDTO(var)));
        }
        return listDto;
    }

    @Override
    public OrderDTO getById(Integer id) {
        OrderDTO dto = new OrderDTO();
        if(orderRepository.findById(id).isPresent()){
            Order order = orderRepository.findById(id).get();
            dto = new OrderDTO(order);
            return dto;
        }
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        try{
            orderRepository.deleteById(id);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean save(Order entity) {
        try{
            orderRepository.save(entity);
            return  true;
        }catch (Exception e) {
            return false;
        }
    }
}
