package com.example.demokoro.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private Byte status;

    @Column(name = "stt")
    private Integer stt;

    @OneToMany(mappedBy="category",cascade = CascadeType.ALL)
    private List<CategoryProduct> categoryProduct;
}
