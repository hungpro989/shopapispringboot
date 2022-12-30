package com.example.demokoro.models;

import com.example.demokoro.dto.ProductDetailCreateDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "product_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "code_name")
    private String codeName;

    @Column(name = "description")
    private String description;

    @Column(name = "desc_short")
    private String descShort;

    @Column(name = "total_quantity")
    private Integer totalQuantity;

    @Column(name = "valid_quantity")
    private Integer validQuantity;

    @Column(name = "hold_quantity")
    private Integer holdQuantity;

    @Column(name = "price")
    private Float price;

    @Column(name = "discount")
    private Float discount;

    @Column(name = "image")
    private String image;

    @Column(name = "status")
    private Integer status;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    //quan he vs product
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="product_id")
    private Product products;

    //quan hệ với order detail
    @OneToMany(mappedBy = "productDetail")
    private List<OrderDetail> orderDetail;

    public ProductDetail(ProductDetailCreateDTO dto) {
        this.id = dto.getId();
        this.codeName = dto.getCodeName();
        this.description = dto.getDescription();
        this.descShort = dto.getDescShort();
        this.totalQuantity = dto.getTotalQuantity();
        this.validQuantity = dto.getValidQuantity();
        this.holdQuantity = dto.getHoldQuantity();
        this.price = dto.getPrice();
        this.discount = dto.getDiscount();
        this.image = dto.getImage();
        this.status = dto.getStatus();
    }
}
