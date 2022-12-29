package com.example.demokoro.models;

import com.example.demokoro.dto.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "seller_id")
    private Integer sellerId;

    @Column(name = "creator_id")
    private Integer creatorId;

    @Column(name = "business_id")
    private Integer businessId;

    @Column(name = "delivery_id")
    private Integer deliveryId;

//    @Column(name = "status_id")
//    private Integer statusId;

    @Column(name = "type_id")
    private Integer typeId;

    @Column(name = "total_money")
    private Double totalMoney;

    @Column(name = "product_money")
    private Double productMoney;

    @Column(name = "shipping_price")
    private Double shippingPrice;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "paid")
    private Double paid;

    @Column(name = "payment_amount")
    private Double paymentAmount;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "bill_code")
    private String billCode;

    @Column(name = "internal_notes")
    private String internalNotes;

    @Column(name = "shipping_notes")
    private String shippingNotes;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "order_time")
    private java.sql.Timestamp orderTime;

    @Column(name = "shipping_time")
    private java.sql.Timestamp shippingTime;

    @Column(name = "created_at")
    private java.sql.Timestamp createdAt;

    @Column(name = "updated_at")
    private java.sql.Timestamp updatedAt;

    @ManyToOne()
    private OrderStatus orderStatus;

    public Order(OrderDTO dto) {
        this.id = dto.getId();
        this.sellerId = dto.getSellerId();
        this.creatorId = dto.getCreatorId();
        this.businessId = dto.getBusinessId();
        this.deliveryId = dto.getDeliveryId();

        this.typeId = dto.getTypeId();
        this.totalMoney = dto.getTotalMoney();
        this.productMoney = dto.getProductMoney();
        this.shippingPrice = dto.getShippingPrice();
        this.discount = dto.getDiscount();
        this.paid = dto.getPaid();
        this.paymentAmount = dto.getPaymentAmount();
        this.customerId = dto.getCustomerId();
        this.billCode = dto.getBillCode();
        this.internalNotes = dto.getInternalNotes();
        this.shippingNotes = dto.getShippingNotes();
        this.name = dto.getName();
        this.phone = dto.getPhone();
        this.address = dto.getAddress();
        this.orderTime = dto.getOrderTime();
        this.shippingTime = dto.getShippingTime();
        this.createdAt = dto.getCreatedAt();
        this.updatedAt = dto.getUpdatedAt();

    }
//n-1

}
