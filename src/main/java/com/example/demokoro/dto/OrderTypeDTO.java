package com.example.demokoro.dto;

import com.example.demokoro.models.OrderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderTypeDTO {
    private Integer id;
    private String name;
    private Boolean status;
    private Integer stt;

    public OrderTypeDTO(OrderType d){
        this.id=d.getId();
        this.name=d.getName();
        this.status=d.getStatus();
        this.stt=d.getStt();
    }
}
