package com.example.demokoro.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
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

}
