package com.example.demokoro.serviceImpl;

import com.example.demokoro.dto.DeliveryDTO;
import com.example.demokoro.models.Delivery;

import java.util.List;

public interface IDeliveryService {
    List<DeliveryDTO> getAll();
    DeliveryDTO getById(Integer id);
    boolean deleteById(Integer id);
    boolean save(Delivery delivery);
    boolean checkExistName(String name);
    boolean checkExistId(Integer id);
}
