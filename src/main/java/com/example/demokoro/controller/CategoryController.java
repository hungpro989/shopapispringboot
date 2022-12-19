package com.example.demokoro.controller;

import com.example.demokoro.dto.CategoryDTOAdmin;
import com.example.demokoro.dto.ResponseObject;
import com.example.demokoro.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @GetMapping("/categories")
    public List<CategoryDTOAdmin> getAllCategory(){
        return categoryService.getAllCategory();
    }
    @GetMapping("/category/{id}")
    public ResponseEntity<ResponseObject> getCategoryById(@PathVariable Integer id){
        if (categoryService.getCategoryById(id)==null){
            return ResponseEntity.ok().body(new ResponseObject("Không tìm thấy category có id như vậy","false",null));
        }
        return ResponseEntity.ok().body(new ResponseObject("Lấy category thành công","true",categoryService.getCategoryById(id)));
    }
}
