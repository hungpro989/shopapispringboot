package com.example.demokoro.service;

import com.example.demokoro.dto.EmployeeDTO;
import com.example.demokoro.dto.OrderTypeDTO;
import com.example.demokoro.models.Employee;
import com.example.demokoro.models.OrderType;
import com.example.demokoro.repository.EmployeeRepository;
import com.example.demokoro.serviceImpl.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Override
    public List<EmployeeDTO> getAll() {
        List<EmployeeDTO> listDto = new ArrayList<>();
        List<Employee> list = employeeRepository.findAll();
        for(Employee var: list){
            listDto.add((new EmployeeDTO(var)));
        }
        return listDto;
    }

    @Override
    public EmployeeDTO getById(Integer id) {
        EmployeeDTO dto = new EmployeeDTO();
        if(employeeRepository.findById(id).isPresent()){
            Employee employe = employeeRepository.findById(id).get();
            dto = new EmployeeDTO(employe);
            return dto;
        }
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        try{
            employeeRepository.deleteById(id);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean save(Employee entity) {
        try{
            employeeRepository.save(entity);
            return  true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean checkExistEmail(String email) {
        if(employeeRepository.findEmployeeByEmail(email)!=null){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkExistPhone(String phone) {
        if(employeeRepository.findEmployeeByPhone(phone)!=null){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkExistId(Integer id) {
        if(employeeRepository.findById(id).orElse(null)!=null){
            return true;
        }
        return false;
    }

    @Override
    public List<Employee> findEmployeeWhereNotId(Integer id, String email, String phone) {
        try {
            List<Employee> list = employeeRepository.getEmployeeWhereNotId(id, email, phone);
            return list;
        } catch (Exception e) {
            return null;
        }
    }
}
