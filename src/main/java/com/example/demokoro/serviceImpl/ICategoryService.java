package com.example.demokoro.serviceImpl;

import com.example.demokoro.dto.CategoryDTOAdmin;
import com.example.demokoro.models.Category;

import java.util.List;

public interface ICategoryService {
    List<CategoryDTOAdmin> getAllCategory();
    CategoryDTOAdmin getCategoryById(Integer id);
    boolean deleteById(Integer id);
     boolean save(Category category);
     boolean checkExistName(String name);
     boolean checkExistId(Integer id);
}
