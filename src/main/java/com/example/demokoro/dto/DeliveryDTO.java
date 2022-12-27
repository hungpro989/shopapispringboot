package com.example.demokoro.dto;

import com.example.demokoro.models.Delivery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDTO {
    private Integer id;
    private String codeName;
    private String fullName;
    private String token;

    public DeliveryDTO(Delivery d){
        this.id=d.getId();
        this.codeName=d.getCodeName();
        this.fullName=d.getFullName();
        this.token=d.getToken();
    }
}
