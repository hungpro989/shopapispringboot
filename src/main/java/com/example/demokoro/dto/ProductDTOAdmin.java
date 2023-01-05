package com.example.demokoro.dto;

import com.example.demokoro.models.CategoryProduct;
import com.example.demokoro.models.Product;
import com.example.demokoro.models.ProductDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTOAdmin {
    private Integer id;
    private String content;
    private String contentShort;
    private java.sql.Timestamp createdAt;
    private String image;
    private String linkOrder;
    private String name;
    private String slug;
    private Byte status;
    private java.sql.Timestamp updatedAt;
    private List<CategoryDTOAdmin> categoryProduct;
    private List<ProductDetailDTO> productDetail;
    public ProductDTOAdmin(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.content = product.getContent();
        this.contentShort= product.getContentShort();
        this.linkOrder= product.getLinkOrder();
        this.image=product.getImage();
        this.slug= product.getSlug();
        this.status= product.getStatus();
        this.createdAt=product.getCreatedAt();
        this.updatedAt=product.getUpdatedAt();

        //category
        List<CategoryProduct> productCategories = product.getCategoryProduct();
        List<CategoryDTOAdmin> categoryDTOAdmin= new ArrayList<>();
        if(productCategories==null){
            this.categoryProduct = categoryDTOAdmin;
        }else{
            productCategories.forEach(c -> {
                categoryDTOAdmin.add(new CategoryDTOAdmin(c));
            });
            this.categoryProduct = categoryDTOAdmin;
        }


        //product detail
        List<ProductDetail> productDetail = product.getProductDetail();//lay ra
        List<ProductDetailDTO> productDetailDTO = new ArrayList<>();//tao mang moi
        if(productDetail==null){
            this.productDetail = productDetailDTO;
        }else {
            productDetail.forEach(p -> {
                productDetailDTO.add(new ProductDetailDTO(p));
            });
            this.productDetail = productDetailDTO;
        }
    }
}
