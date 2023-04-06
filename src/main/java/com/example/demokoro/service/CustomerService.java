package com.example.demokoro.service;

import com.example.demokoro.dto.CustomerViewDTO;
import com.example.demokoro.models.Customer;
import com.example.demokoro.repository.CustomerRepository;
import com.example.demokoro.serviceImpl.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CustomerService implements ICustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public List<CustomerViewDTO> getAll() {
        List<CustomerViewDTO> listDto = new ArrayList<>();
        List<Customer> list = customerRepository.findAll();
        for(Customer var: list){
            listDto.add((new CustomerViewDTO(var)));
        }
        return listDto;
    }

    @Override
    public CustomerViewDTO getById(Integer id) {
        if(customerRepository.findById(id).isPresent()){
            Customer customer = customerRepository.findById(id).get();
            return new CustomerViewDTO(customer);
        }
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        try{
            customerRepository.deleteById(id);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean save(Customer entity) {
        try{
            customerRepository.save(entity);
            return  true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean checkExistEmail(String email) {
        return customerRepository.findCustomerByEmail(email) != null;
    }

    @Override
    public CustomerViewDTO checkExistPhone(String phone) {
        Customer customer =  customerRepository.findCustomerByPhone(phone);
        CustomerViewDTO dto = new CustomerViewDTO(customer);
        return dto;
    }

    @Override
    public boolean checkExistId(Integer id) {
        return customerRepository.findById(id).orElse(null) != null;
    }
    @Override
    public boolean checkExistUsername(String username) {
        return customerRepository.findCustomerByUsername(username) !=null;
    }
}
