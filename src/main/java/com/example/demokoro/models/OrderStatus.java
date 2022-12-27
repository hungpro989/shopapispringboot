package com.example.demokoro.models;

import com.example.demokoro.dto.DeliveryDTO;
import com.example.demokoro.dto.OrderStatusDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "order_status")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "stt")
    private Integer stt;

    public OrderStatus(OrderStatusDTO dto) {
        this.id=dto.getId();
        this.name=dto.getName();
        this.status=dto.getStatus();
        this.stt=dto.getStt();
    }
}
