package com.example.demokoro.dto;

import com.example.demokoro.models.Category;
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
}
