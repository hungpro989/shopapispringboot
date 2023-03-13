package com.example.demokoro.dto;

import com.example.demokoro.models.OrderDetail;
import com.example.demokoro.models.ProductDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailCreateDTO {
    private Integer id;
    private Integer orderId;
    private Integer proDeId;
    private Float price;
    private Float discount;
    private Integer quantity;
//    private java.sql.Timestamp createdAt;
//    private java.sql.Timestamp updatedAt;
//    private ProductDetailDTO productDetailDTO;

    public  OrderDetailCreateDTO(OrderDetail o){
        this.id=o.getId();
        this.orderId=o.getOrders().getId();
        this.proDeId=o.getProductDetail().getId();
        this.price=o.getPrice();
        this.discount=o.getDiscount();
        this.quantity=o.getQuantity();
//        ProductDetail productDetail1 = o.getProductDetail();
//        this.productDetailDTO= new ProductDetailDTO(productDetail1);
    }
}
