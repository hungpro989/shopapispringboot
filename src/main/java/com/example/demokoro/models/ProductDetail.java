package com.example.demokoro.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "product_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetail {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "code_name")
    private String codeName;

    @Column(name = "desc")
    private String desc;

    @Column(name = "desc_short")
    private String descShort;

    @Column(name = "total_quantity")
    private Integer totalQuantity;

    @Column(name = "valid_quantity")
    private Integer validQuantity;

    @Column(name = "hold_quantity")
    private Integer holdQuantity;

    @Column(name = "price")
    private Float price;

    @Column(name = "discount")
    private Float discount;

    @Column(name = "image")
    private String image;

    @Column(name = "status")
    private Integer status;

    @Column(name = "created_at")
    private java.sql.Timestamp createdAt;

    @Column(name = "updated_at")
    private java.sql.Timestamp updatedAt;

    //quan he vs product
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="product_id")
    @JsonIgnoreProperties("Product_Detail")
    private ProductDetail productDetail;
}
