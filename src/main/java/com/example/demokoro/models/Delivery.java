package com.example.demokoro.models;

import com.example.demokoro.dto.DeliveryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "delivery")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "code_name")
    private String codeName;

    @Column(name = "name")
    private String name;

    @Column(name = "token")
    private String token;
    @Column(name = "data_status")
    private String dataStatus;

    //order_delivery
    @OneToMany(mappedBy="order", cascade = CascadeType.ALL)
    private List<OrderDelivery> orderDelivery;

    public Delivery(DeliveryDTO dto) {
        this.id=dto.getId();
        this.codeName=dto.getCodeName();
        this.name=dto.getName();
        this.token=dto.getToken();
        this.dataStatus=dto.getDataStatus();
    }
}
