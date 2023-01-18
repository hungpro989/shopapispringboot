package com.example.demokoro.service;

import com.example.demokoro.dto.EmployeeDTO;
import com.example.demokoro.models.Employee;
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
        if(employeeRepository.findById(id).isPresent()){
            Employee employee = employeeRepository.findById(id).get();
            return new EmployeeDTO(employee);
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
        return employeeRepository.findEmployeeByEmail(email) != null;
    }

    @Override
    public boolean checkExistPhone(String phone) {
        return employeeRepository.findEmployeeByPhone(phone) != null;
    }

    @Override
    public boolean checkExistId(Integer id) {
        return employeeRepository.findById(id).orElse(null) != null;
    }

    @Override
    public List<Employee> findEmployeeWhereNotId(Integer id, String email, String phone) {
        try {
            return employeeRepository.getEmployeeWhereNotId(id, email, phone);
        } catch (Exception e) {
            return null;
        }
    }



}
