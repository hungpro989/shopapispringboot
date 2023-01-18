package com.example.demokoro.service;

import com.example.demokoro.dto.UserDTO;
import com.example.demokoro.models.User;
import com.example.demokoro.repository.UserRepository;
import com.example.demokoro.serviceImpl.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
@Service
public class UserService implements IUserService, UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public List<UserDTO> getAll() {
        List<UserDTO> listDto = new ArrayList<>();
        List<User> list = userRepository.findAll();
        for(User var: list){
            listDto.add((new UserDTO(var)));
        }
        return listDto;
    }

    @Override
    public UserDTO getById(Integer id) {
        if(userRepository.findById(id).isPresent()){
            User user = userRepository.findById(id).get();
            return new UserDTO(user);
        }
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        try{
            userRepository.deleteById(id);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean save(User entity) {
        try{
            userRepository.save(entity);
            return  true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean checkExistEmail(String email) {
        return userRepository.findEmployeeByEmail(email) != null;
    }

    @Override
    public boolean checkExistPhone(String phone) {
        return userRepository.findEmployeeByPhone(phone) != null;
    }

    @Override
    public boolean checkExistId(Integer id) {
        return userRepository.findById(id).orElse(null) != null;
    }

    @Override
    public List<User> findEmployeeWhereNotId(Integer id, String email, String phone) {
        try {
            return userRepository.getEmployeeWhereNotId(id, email, phone);
        } catch (Exception e) {
            return null;
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) {
        // Kiểm tra xem user có tồn tại trong database không?
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }

    // JWTAuthenticationFilter sẽ sử dụng hàm này
    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(Math.toIntExact(id)).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );

        return new CustomUserDetails(user);
    }
}
