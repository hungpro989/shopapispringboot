package com.example.demokoro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateDTO {
    private Integer id;
    private Integer userId;
    private Integer creatorId;
    private Integer businessId;
    private Integer deliveryId;
    private Integer statusId;
    private Integer typeId;
    private double totalMoney;
    private double productMoney;
    private double shippingPrice;
    private double discount;
    private double paid;
    private double paymentAmount;
    private Integer customerId;
    private String billCode;
    private String internalNotes;
    private String shippingNotes;
    private String name;
    private String phone;
    private String address;
    private Date orderTime;
    private List<OrderDetailDTO> orderDetailDTO;

}
