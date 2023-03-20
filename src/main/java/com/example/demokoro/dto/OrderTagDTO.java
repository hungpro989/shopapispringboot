package com.example.demokoro.dto;

import com.example.demokoro.models.OrderTag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderTagDTO {
//    private Integer id;
//    private Integer orderId;
    private Integer id;
    private String name ;

    public OrderTagDTO(OrderTag p) {
//        this.id = p.getId();
//        this.orderId = p.getOrder().getId();
        this.id = p.getTag().getId();
    this.name = p.getTag().getName();
    }
}
