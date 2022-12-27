package com.example.demokoro.dto;

import com.example.demokoro.models.Delivery;
import com.example.demokoro.models.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusDTO {
    private Integer id;
    private String name;
    private Boolean status;
    private Integer stt;

    public OrderStatusDTO(OrderStatus d){
        this.id=d.getId();
        this.name=d.getName();
        this.status=d.getStatus();
        this.stt=d.getStt();
    }
}
