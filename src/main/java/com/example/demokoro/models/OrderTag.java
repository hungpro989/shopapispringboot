package com.example.demokoro.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "order_tag")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="order_id", referencedColumnName = "id")
    private Order order;

    @ManyToOne
    @JoinColumn(name="tag_id", referencedColumnName = "id")
    private Tag tag;
}
