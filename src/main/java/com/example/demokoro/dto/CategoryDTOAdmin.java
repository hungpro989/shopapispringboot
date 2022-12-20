package com.example.demokoro.dto;

import com.example.demokoro.models.Category;
import com.example.demokoro.models.CategoryProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTOAdmin {
    private Integer id;
    private String name;

    public CategoryDTOAdmin(Category c) {
        this.id=c.getId();
        this.name=c.getName();
    }

    public CategoryDTOAdmin(CategoryProduct p) {
        this.id=p.getCategory().getId();
        this.name=p.getCategory().getName();
    }
}
