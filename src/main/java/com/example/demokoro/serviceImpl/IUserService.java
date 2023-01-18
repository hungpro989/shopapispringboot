package com.example.demokoro.serviceImpl;

import com.example.demokoro.dto.UserDTO;
import com.example.demokoro.models.User;

import java.util.List;

public interface IUserService {
    List<UserDTO> getAll();
    UserDTO getById(Integer id);
    boolean deleteById(Integer id);
    boolean save(User entity);
    boolean checkExistEmail(String email);
    boolean checkExistPhone(String phone);
    boolean checkExistId(Integer id);
    List<User> findEmployeeWhereNotId(Integer id, String email, String phone);

}
