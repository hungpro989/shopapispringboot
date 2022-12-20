package com.example.demokoro.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "category_product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    //qhe voi category
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="category_id")
    @JsonIgnoreProperties("product_category")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Category category;
    //qhe voi product
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="product_id")
    @JsonIgnoreProperties("product_category")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Product product;
}
