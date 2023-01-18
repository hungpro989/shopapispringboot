package com.example.demokoro.serviceImpl;

import com.example.demokoro.dto.CustomerViewDTO;
import com.example.demokoro.models.Customer;

import java.util.List;

public interface ICustomerService {
    List<CustomerViewDTO> getAll();
    CustomerViewDTO getById(Integer id);
    boolean deleteById(Integer id);
    boolean save(Customer entity);
    boolean checkExistEmail(String email);
    boolean checkExistPhone(String phone);
    boolean checkExistId(Integer id);

    boolean checkExistUsername(String username);
}
