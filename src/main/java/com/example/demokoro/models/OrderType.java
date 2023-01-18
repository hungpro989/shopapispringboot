package com.example.demokoro.models;

import com.example.demokoro.dto.OrderTypeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "order_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderType {
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

    @OneToMany(mappedBy = "orderType")
    private List<Order> order;
    public OrderType(OrderTypeDTO dto) {
        this.id=dto.getId();
        this.name=dto.getName();
        this.status=dto.getStatus();
        this.stt=dto.getStt();
    }
}
