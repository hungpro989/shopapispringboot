package com.example.demokoro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryProductDTO {
    private Integer id;
    private Integer categoryId;
    private Integer productId;


}
