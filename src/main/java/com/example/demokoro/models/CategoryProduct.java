package com.example.demokoro.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "category_product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryProduct {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "product_id")
    private Integer productId;

}
