package com.example.demokoro.serviceImpl;

import com.example.demokoro.dto.CategoryDTOAdmin;
import com.example.demokoro.models.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<CategoryDTOAdmin> getAllCategory();
    CategoryDTOAdmin getCategoryById(Integer id);
}
