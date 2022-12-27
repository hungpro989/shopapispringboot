package com.example.demokoro.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "category_product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    //qhe voi category
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    //qhe voi product
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="product_id", referencedColumnName = "id")
    private Product product;

}
