package com.example.demokoro.service;

import com.example.demokoro.dto.DeliveryDTO;
import com.example.demokoro.models.Delivery;
import com.example.demokoro.repository.DeliveryRepository;
import com.example.demokoro.serviceImpl.IDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryService implements IDeliveryService {
    @Autowired
    DeliveryRepository deliveryRepository;
    @Override
    public List<DeliveryDTO> getAll() {
        List<DeliveryDTO> listDto = new ArrayList<>();
        List<Delivery> list = deliveryRepository.findAll();
        for(Delivery var: list){
            listDto.add((new DeliveryDTO(var)));
        }
        return listDto;
    }

    @Override
    public DeliveryDTO getById(Integer id) {
        DeliveryDTO dto = new DeliveryDTO();
        if(deliveryRepository.findById(id).isPresent()){
            Delivery delivery = deliveryRepository.findById(id).get();
            dto = new DeliveryDTO(delivery);
            return dto;
        }
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        try{
            deliveryRepository.deleteById(id);
            return true;
        }catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean save(Delivery delivery) {
        try{
            deliveryRepository.save(delivery);
            return  true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean checkExistName(String name) {
        if (deliveryRepository.findDeliveryByCodeName(name)!=null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkExistId(Integer id) {
        if(deliveryRepository.findById(id).orElse(null)!=null){
            return true;
        }
        return false;
    }
}
