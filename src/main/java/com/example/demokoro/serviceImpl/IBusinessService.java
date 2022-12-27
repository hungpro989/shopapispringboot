package com.example.demokoro.serviceImpl;

import com.example.demokoro.dto.BusinessDTO;
import com.example.demokoro.models.Business;

import java.util.List;

public interface IBusinessService {
    List<BusinessDTO> getAllBusiness();
    BusinessDTO getBusinessById(Integer id);
    boolean deleteById(Integer id);
    boolean save(Business category);
    boolean checkExistName(String name);
    boolean checkExistId(Integer id);
}
