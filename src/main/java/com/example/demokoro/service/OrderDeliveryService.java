package com.example.demokoro.service;

import com.example.demokoro.dto.OrderDeliveryDTO;
import com.example.demokoro.models.OrderDelivery;
import com.example.demokoro.repository.DeliveryRepository;
import com.example.demokoro.repository.OrderDeliveryRepository;
import com.example.demokoro.repository.OrderRepository;
import com.example.demokoro.serviceImpl.IOrderDeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderDeliveryService implements IOrderDeliveryRepository {
    @Autowired
    OrderDeliveryRepository orderDeliveryRepository;
    @Autowired
    DeliveryRepository deliveryRepository;
    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<OrderDelivery> getAll() {
        List<OrderDelivery> list = orderDeliveryRepository.findAll();
        return list;
    }

    @Override
    public OrderDeliveryDTO getById(Integer id) {
        OrderDeliveryDTO dto = new OrderDeliveryDTO();
        OrderDelivery o = orderDeliveryRepository.findById(id).orElse(null);
        if (o !=null) {
            dto = new OrderDeliveryDTO(o);
            return dto;
        }
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        try{
            orderDeliveryRepository.deleteById(id);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean save(OrderDeliveryDTO dto) {
        try{
            OrderDelivery orderDelivery = new OrderDelivery(dto);
            orderDelivery.setDelivery(deliveryRepository.findById(dto.getDeliveryId()).orElse(null));
            orderDelivery.setOrder(orderRepository.findById(dto.getOrderId()).orElse(null));
            if(checkExistCodeDelivery(dto.getCodeDelivery())){
                orderDeliveryRepository.save(orderDelivery);
            }
            return  true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean checkExistCodeDelivery(String s) {
        return orderDeliveryRepository.findOrderDeliveryByCodeDelivery(s)== null;
    }

    @Override
    public OrderDeliveryDTO getByOrderId(Integer id) {
        OrderDeliveryDTO dto = new OrderDeliveryDTO();
        OrderDelivery o = orderDeliveryRepository.findOrderDeliveryByOrderId(id);
        if (o !=null) {
            dto = new OrderDeliveryDTO(o);
            return dto;
        }
        return null;
    }
}
