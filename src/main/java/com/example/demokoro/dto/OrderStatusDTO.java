package com.example.demokoro.dto;

import com.example.demokoro.models.Order;
import com.example.demokoro.models.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusDTO {
    private Integer id;
    private String name;
//    private Boolean status;
//    private Integer stt;
    //private List<OrderDTO> orderDTO;
    public OrderStatusDTO(OrderStatus d){
        this.id=d.getId();
        this.name=d.getName();
//        this.status=d.getStatus();
//        this.stt=d.getStt();
        //list order
//        List<Order> orders = d.getOrder();//lay ra
//        List<OrderDTO> orderDTO = new ArrayList<>();//tao mang moi
//        orders.forEach(p-> {
//            orderDTO.add(new OrderDTO(p));
//        });
        //this.orderDTO = orderDTO;
    }
}
