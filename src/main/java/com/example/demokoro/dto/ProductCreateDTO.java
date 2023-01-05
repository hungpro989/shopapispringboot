package com.example.demokoro.dto;

import com.example.demokoro.models.CategoryProduct;
import com.example.demokoro.models.Product;
import com.example.demokoro.models.ProductDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateDTO {
    private Integer id;
    private String name;
    private String slug;
    private String content;
    private String contentShort;
    private String image;
    private String linkOrder;
    private Byte status;
    private List<ProductDetailCreateDTO> productDetail;
    private List<CategoryProductCreateDTO> categoryProduct;

    public ProductCreateDTO(ProductDTOAdmin productDTOAdmin) {
        this.name = productDTOAdmin.getName();
        this.slug = productDTOAdmin.getSlug();
        this.content = productDTOAdmin.getContent();
        this.contentShort = productDTOAdmin.getContentShort();
        this.image=productDTOAdmin.getImage();
        this.linkOrder = productDTOAdmin.getLinkOrder();
        this.status=productDTOAdmin.getStatus();

    }
}
