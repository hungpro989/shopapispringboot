package com.example.demokoro.service;

import com.example.demokoro.models.OrderTag;
import com.example.demokoro.repository.OrderTagRepository;
import com.example.demokoro.serviceImpl.IOrderTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderTagService implements IOrderTagService {
    @Autowired
    OrderTagRepository orderTagRepository;
    @Override
    public void save(OrderTag ot) {
        orderTagRepository.save(ot);
    }
    @Override
    public List<OrderTag> findOrderTagByTagId(Integer id) {
        return orderTagRepository.findOrderTagByTagId(id);
    }
    @Override
    @Transactional
    public boolean deleteOrderTagByOrderId(Integer orderId) {
        try{
            orderTagRepository.deleteOrderTagByOrderId(orderId);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
