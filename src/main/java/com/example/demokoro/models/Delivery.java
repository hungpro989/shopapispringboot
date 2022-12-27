package com.example.demokoro.models;

import com.example.demokoro.dto.DeliveryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "token")
    private String token;

    public Delivery(DeliveryDTO dto) {
        this.id=dto.getId();
        this.codeName=dto.getCodeName();
        this.fullName=dto.getFullName();
        this.token=dto.getToken();
    }
}
