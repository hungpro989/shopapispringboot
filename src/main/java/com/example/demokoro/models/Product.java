package com.example.demokoro.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "content")
    private String content;

    @Column(name = "content_short")
    private String contentShort;

    @Column(name = "created_at")
    private java.sql.Timestamp createdAt;

    @Column(name = "image")
    private String image;

    @Column(name = "link_order")
    private String linkOrder;

    @Column(name = "name")
    private String name;

    @Column(name = "slug")
    private String slug;

    @Column(name = "status")
    private Byte status;

    @Column(name = "updated_at")
    private java.sql.Timestamp updatedAt;

    //quan hệ với category
    @JsonManagedReference
    @OneToMany(mappedBy="product", orphanRemoval=true, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("product")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<CategoryProduct> categoryProduct =new ArrayList<CategoryProduct>();

    //quan hệ với product detail
    @JsonManagedReference
    @OneToMany(mappedBy="productDetail", orphanRemoval=true, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("product")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<ProductDetail> productDetail =new ArrayList<ProductDetail>();
}
