package com.example.demokoro.models;

import com.example.demokoro.dto.ProductDetailCreateDTO;
import com.example.demokoro.dto.ProductDetailDTO;
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

    @Column(name = "cost")
    private Float cost;

    @Column(name = "price")
    private Float price;

    @Column(name = "discount")
    private Float discount;

    @Column(name = "image")
    private String image;

    @Column(name = "status")
    private Boolean status;

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
        this.cost = dto.getCost();
    }

    public ProductDetail(ProductDetailDTO var) {
        this.id = var.getId();
        this.codeName = var.getCodeName();
        this.description = var.getDescription();
        this.descShort = var.getDescShort();
        this.totalQuantity = var.getTotalQuantity();
        this.validQuantity = var.getValidQuantity();
        this.holdQuantity = var.getHoldQuantity();
        this.price = var.getPrice();
        this.discount = var.getDiscount();
        this.image = var.getImage();
        this.status = var.getStatus();
        this.cost=var.getCost();
    }

    public ProductDetail(ProductDetail pd) {
        this.id = pd.getId();
        this.codeName = pd.getCodeName();
        this.description = pd.getDescription();
        this.descShort = pd.getDescShort();
        this.totalQuantity = pd.getTotalQuantity();
        this.validQuantity = pd.getValidQuantity();
        this.holdQuantity = pd.getHoldQuantity();
        this.cost = pd.getCost();
        this.price = pd.getPrice();
        this.discount = pd.getDiscount();
        this.image = pd.getImage();
        this.status = pd.getStatus();
        this.products = pd.getProducts();
    }
}
