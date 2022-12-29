package com.example.demokoro.dto;

import com.example.demokoro.models.Order;
import com.example.demokoro.models.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Integer id;

    private Integer sellerId;
    private Integer creatorId;
    private Integer businessId;
    private Integer deliveryId;
    //private Integer statusId;
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
    private java.sql.Timestamp orderTime;
    private java.sql.Timestamp shippingTime;
    private java.sql.Timestamp createdAt;
    private java.sql.Timestamp updatedAt;
    private OrderStatusDTO orderStatusDTO;
    public OrderDTO(Order o) {
        this.id = o.getId();
        this.sellerId = o.getSellerId();
        this.creatorId = o.getCreatorId();
        this.businessId = o.getBusinessId();
        this.deliveryId = o.getDeliveryId();
        //this.statusId = o.getStatusId();
        this.typeId = o.getTypeId();
        this.totalMoney = o.getTotalMoney();
        this.productMoney = o.getProductMoney();
        this.shippingPrice = o.getShippingPrice();
        this.discount = o.getDiscount();
        this.paid = o.getPaid();
        this.paymentAmount = o.getPaymentAmount();
        this.customerId = o.getCustomerId();
        this.billCode = o.getBillCode();
        this.internalNotes = o.getInternalNotes();
        this.shippingNotes = o.getShippingNotes();
        this.name = o.getName();
        this.phone = o.getPhone();
        this.address = o.getAddress();
        this.orderTime = o.getOrderTime();
        this.shippingTime = o.getShippingTime();
        this.createdAt = o.getCreatedAt();
        this.updatedAt = o.getUpdatedAt();

        // lấy ra statusOrder
        OrderStatus orderStatus = o.getOrderStatus();
        OrderStatusDTO orderStatusDTO = new OrderStatusDTO();
        orderStatusDTO.setId(orderStatus.getId());
        orderStatusDTO.setName(orderStatus.getName());
        this.orderStatusDTO = orderStatusDTO;
    }
}
