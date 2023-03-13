package com.example.demokoro.models;

import com.example.demokoro.dto.OrderCreateDTO;
import com.example.demokoro.dto.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    @Column(name = "bill_code", unique = true, nullable = false)
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
    @Column(name = "province")
    private String province;
    @Column(name = "district")
    private String district;
    @Column(name = "wards")
    private String wards;
    @CreationTimestamp
    @Column(name = "order_time", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date orderTime;

    @Column(name = "shipping_time")
    private Date shippingTime;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

//status
    @ManyToOne()
    private OrderStatus orderStatus;
//business
    @ManyToOne()
    private Business business;
//delivery
    @ManyToOne()
    private Delivery delivery;
//type
    @ManyToOne()
    private OrderType orderType;
    //seller
    @ManyToOne()
    @JoinColumn(name = "user_id", nullable=false)
    private User user;
    //creator
    @ManyToOne()
    @JoinColumn(name = "creator_id", nullable=false)
    private User user1;

    //order detail
    @OneToMany(mappedBy="orders", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetail;

    //customer
    @ManyToOne()
    @JoinColumn(name = "customer_id", nullable=false)
    private Customer customer;

    //tag
    @OneToMany(mappedBy="order", cascade = CascadeType.ALL)
    private List<OrderTag> orderTag;

    public Order(OrderDTO dto) {
        this.id = dto.getId();
        this.totalMoney = dto.getTotalMoney();
        this.productMoney = dto.getProductMoney();
        this.shippingPrice = dto.getShippingPrice();
        this.discount = dto.getDiscount();
        this.paid = dto.getPaid();
        this.paymentAmount = dto.getPaymentAmount();
        this.billCode = dto.getBillCode();
        this.internalNotes = dto.getInternalNotes();
        this.shippingNotes = dto.getShippingNotes();
        this.name = dto.getName();
        this.phone = dto.getPhone();
        this.address = dto.getAddress();
        this.orderTime = dto.getOrderTime();
        this.shippingTime = dto.getShippingTime();
        this.province = dto.getProvince();
        this.district = dto.getDistrict();
        this.wards = dto.getWards();
        this.createdAt = dto.getCreatedAt();
        this.updatedAt = dto.getUpdatedAt();
    }

    public Order(OrderCreateDTO orderCreateDTO) {
        this.id = orderCreateDTO.getId();
        this.totalMoney = orderCreateDTO.getTotalMoney();
        this.productMoney = orderCreateDTO.getProductMoney();
        this.shippingPrice = orderCreateDTO.getShippingPrice();
        this.discount = orderCreateDTO.getDiscount();
        this.paid = orderCreateDTO.getPaid();
        this.paymentAmount = orderCreateDTO.getPaymentAmount();
        this.billCode = orderCreateDTO.getBillCode();
        this.internalNotes = orderCreateDTO.getInternalNotes();
        this.shippingNotes = orderCreateDTO.getShippingNotes();
        this.name = orderCreateDTO.getName();
        this.phone = orderCreateDTO.getPhone();
        this.address = orderCreateDTO.getAddress();
        this.orderTime = orderCreateDTO.getOrderTime();
        this.province = orderCreateDTO.getProvince();
        this.district = orderCreateDTO.getDistrict();
        this.wards = orderCreateDTO.getWards();
    }

}
