package com.example.demokoro.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
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

    @JsonManagedReference
    @OneToMany(mappedBy="category", orphanRemoval=true, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("category")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<CategoryProduct> categoryProduct =new ArrayList<CategoryProduct>();
}
