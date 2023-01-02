package com.example.demokoro.serviceImpl;

import com.example.demokoro.dto.EmployeeDTO;
import com.example.demokoro.models.Employee;

import java.util.List;

public interface IEmployeeService {
    List<EmployeeDTO> getAll();
    EmployeeDTO getById(Integer id);
    boolean deleteById(Integer id);
    boolean save(Employee entity);
    boolean checkExistEmail(String email);
    boolean checkExistPhone(String phone);
    boolean checkExistId(Integer id);
    List<Employee> findEmployeeWhereNotId(Integer id, String email, String phone);
}
