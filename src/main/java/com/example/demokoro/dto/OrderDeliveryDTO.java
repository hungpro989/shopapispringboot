package com.example.demokoro.dto;

import com.example.demokoro.models.OrderDelivery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDeliveryDTO {
    private Integer id;

    private Integer orderId;

    private Integer deliveryId;

    private String codeDelivery;

    private boolean status;

    private String data;

    private Date createdAt;

    private Date updatedAt;


    public OrderDeliveryDTO(OrderDelivery o) {
        this.id = o.getId();
        this.orderId=o.getOrder().getId();
        this.deliveryId=o.getDelivery().getId();
        this.codeDelivery=o.getCodeDelivery();
        this.status=o.isStatus();
        this.data=o.getData();
        this.createdAt=o.getCreatedAt();
        this.updatedAt=o.getUpdatedAt();
    }
}
