package com.example.demokoro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailDTO {
    private Integer id;
    private Integer productId;
    private String codeName;
    private String desc;
    private String descShort;
    private Integer totalQuantity;
    private Integer validQuantity;
    private Integer holdQuantity;
    private Float price;
    private Float discount;
    private String image;
    private Integer status;
    private java.sql.Timestamp createdAt;
    private java.sql.Timestamp updatedAt;

}
