package com.example.demokoro.dto;

import com.example.demokoro.models.Employee;
import com.example.demokoro.models.OrderDetail;
import com.example.demokoro.models.ProductDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {
    private Integer id;
    private Integer orderId;
//    private Integer proDeId;
    private Float price;
    private Integer quantity;
    private java.sql.Timestamp createdAt;
    private java.sql.Timestamp updatedAt;
    private ProductDetailDTO productDetailDTO;
   public  OrderDetailDTO(OrderDetail o){
       this.id=o.getId();
       this.orderId=1;
//       this.proDeId=1;
       this.price=o.getPrice();
       this.quantity=o.getQuantity();

       //lấy product detail
       ProductDetail productDetail = o.getProductDetail();
       this.productDetailDTO = new ProductDetailDTO(productDetail);
   }
}
