package com.example.demokoro.service;

import com.example.demokoro.dto.CategoryDTOAdmin;
import com.example.demokoro.models.Category;
import com.example.demokoro.repository.CategoryRepository;
import com.example.demokoro.serviceImpl.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CategoryService implements ICategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<CategoryDTOAdmin> getAllCategory() {
        List<CategoryDTOAdmin> listDto = new ArrayList<>();
        List<Category> list = categoryRepository.findAll();
        for (Category c : list) {
            listDto.add(new CategoryDTOAdmin(c));
        }
        return listDto;
    }

    @Override
    public CategoryDTOAdmin getCategoryById(Integer id) {
        CategoryDTOAdmin dto = new CategoryDTOAdmin();
        if (categoryRepository.findById(id).isPresent()){
            Category category =  categoryRepository.findById(id).get();
            dto = new CategoryDTOAdmin(category.getId(),category.getName());
            return dto;
        }else
            return null;

    }

    @Override
    public boolean deleteById(Integer id) {
        if (checkExistId(id)){
            try {
                categoryRepository.deleteById(id);
                return true;
            }
            catch(Exception e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean save(Category category) {
        try {
            categoryRepository.save(category);
            return true;
        }catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean checkExistName(String name) {
        Category c = categoryRepository.findCategoryByName(name);
        if (c == null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkExistId(Integer id) {
        Category c = categoryRepository.findById(id).orElse(null);
        if (c == null) {
            return false;
        }
        return true;
    }


}
