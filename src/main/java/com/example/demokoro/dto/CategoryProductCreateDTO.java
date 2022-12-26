package com.example.demokoro.dto;

import com.example.demokoro.models.CategoryProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryProductCreateDTO {
    private Integer categoryId;
}
