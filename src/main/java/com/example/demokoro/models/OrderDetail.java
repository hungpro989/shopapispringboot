package com.example.demokoro.models;

import com.example.demokoro.dto.OrderDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "order_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "price")
    private Float price;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "created_at")
    private java.sql.Timestamp createdAt;

    @Column(name = "updated_at")
    private java.sql.Timestamp updatedAt;
    //quan he vs order
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="order_id")
    private Order orders;

    //quan hệ với product detail
    @ManyToOne
    @JoinColumn(name = "pro_de_id")
    private ProductDetail productDetail;

    public OrderDetail(OrderDetailDTO dto) {
        this.id = dto.getId();
//        this.productDetail = dto;
        this.price =dto.getPrice();
        this.quantity = dto.getQuantity();
        this.createdAt = dto.getCreatedAt();
        this.updatedAt = dto.getUpdatedAt();
    }
}
