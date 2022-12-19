package com.example.demokoro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Integer id;
    private String content;
    private String contentShort;
    private java.sql.Timestamp createdAt;
    private String image;
    private String linkOrder;
    private String name;
    private String slug;
    private Byte status;
    private java.sql.Timestamp updatedAt;

}
