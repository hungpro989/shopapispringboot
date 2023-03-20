package com.example.demokoro.dto;

import com.example.demokoro.models.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Integer id;
    private double totalMoney;
    private double productMoney;
    private double shippingPrice;
    private double discount;
    private double paid;
    private double paymentAmount;
    private String billCode;
    private String internalNotes;
    private String shippingNotes;
    private String name;
    private String phone;
    private String address;
    private Date orderTime;
    private Date shippingTime;
    private String province;
    private String district;
    private String ward;
    private Date createdAt;
    private Date updatedAt;
    private OrderStatusDTO orderStatusDTO;
    private  BusinessDTO businessDTO;
    private  DeliveryDTO deliveryDTO;
    private  OrderTypeDTO orderTypeDTO;
    private UserOrderDTO userOrderDTO;
    private UserOrderDTO employeeCreatorOrderDTO;
    private CustomerViewDTO customerViewDTO;
    private List<OrderDetailDTO> orderDetail;
    private List<OrderTagDTO> orderTag;
    public OrderDTO(Order o) {
        this.id = o.getId();
        this.totalMoney = o.getTotalMoney();
        this.productMoney = o.getProductMoney();
        this.shippingPrice = o.getShippingPrice();
        this.discount = o.getDiscount();
        this.paid = o.getPaid();
        this.paymentAmount = o.getPaymentAmount();
        this.billCode = o.getBillCode();
        this.internalNotes = o.getInternalNotes();
        this.shippingNotes = o.getShippingNotes();
        this.name = o.getName();
        this.phone = o.getPhone();
        this.address = o.getAddress();
        this.orderTime = o.getOrderTime();
        this.shippingTime = o.getShippingTime();
        this.province = o.getProvince();
        this.district = o.getDistrict();
        this.ward = o.getWard();
        this.createdAt = o.getCreatedAt();
        this.updatedAt = o.getUpdatedAt();

        // lấy ra statusOrder
        OrderStatus orderStatus = o.getOrderStatus();
        this.orderStatusDTO = new OrderStatusDTO(orderStatus);

        //lấy ra business
        Business business = o.getBusiness();
        this.businessDTO = new BusinessDTO(business);

        //lấy delivery
        Delivery delivery = o.getDelivery();
        this.deliveryDTO = new DeliveryDTO(delivery);

        //lấy order type
        OrderType orderType = o.getOrderType();
        this.orderTypeDTO = new OrderTypeDTO(orderType);

        //lấy employee
        User user = o.getUser();
        this.userOrderDTO = new UserOrderDTO(user);
        //lay creator
        User creator = o.getUser1();
        this.employeeCreatorOrderDTO = new UserOrderDTO(creator);

        //lay customer
        Customer customer = o.getCustomer();
        this.customerViewDTO = new CustomerViewDTO(customer);

        //lay danh sach order detail
        List<OrderDetail> orderDetail = o.getOrderDetail();//lay ra
        List<OrderDetailDTO> orderDetailDTO = new ArrayList<>();//tao mang moi
        orderDetail.forEach(p-> {
            orderDetailDTO.add(new OrderDetailDTO(p));
        });
        this.orderDetail = orderDetailDTO;

        //lấy danh sách order tag
        List<OrderTag> orderTag = o.getOrderTag();
        List<OrderTagDTO> orderTagDTO = new ArrayList<>();
        orderTag.forEach(p->{
            orderTagDTO.add(new OrderTagDTO(p));
        });
        this.orderTag = orderTagDTO;
    }
}
