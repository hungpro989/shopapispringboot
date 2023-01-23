package com.example.demokoro.controller;

import com.example.demokoro.dto.*;
import com.example.demokoro.models.Product;
import com.example.demokoro.repository.CategoryRepository;
import com.example.demokoro.repository.ProductRepository;
import com.example.demokoro.service.CategoryProductService;
import com.example.demokoro.service.CategoryService;
import com.example.demokoro.service.ProductDetailService;
import com.example.demokoro.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@RequestMapping("/api/v1/products")
@RestController
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductDetailService productDetailService;

    @GetMapping
    public ResponseEntity<ResponseObject> getAllProduct(){
        List<ProductDTOAdmin> listDto = productService.getAllProduct();
        if(!listDto.isEmpty()){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lay danh sach san pham thanh cong", listDto));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Lay danh sach san pham that bai", null));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getProductById(@PathVariable Integer id){
        if(productService.checkExistId(id)){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lấy sản phẩm thành công", productService.getProducById(id)));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Không tìm thấy sản phẩm có id như trên", null));
    }
    @PostMapping
    public ResponseEntity<ResponseObject> createProduct(@RequestBody ProductCreateDTO product){
        Product p = new Product(product);
            if(productService.save(p)){
                //save category_product
                productService.createCategoryProduct(product, p);
                //save product detail
                productService.createProductDetail(product,p);
                return ResponseEntity.ok().body(new ResponseObject("success", "Tạo sản phẩm mới thành công", product));
            }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Tạo sản phẩm thất bại2", null));
    }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponseObject> updateProduct(@RequestBody ProductCreateDTO product, @PathVariable Integer id){
        String check = productService.updateAndCheckProduct(product,id);
        if(Objects.equals(check, "true")){
            return ResponseEntity.ok().body(new ResponseObject("success", "Cập nhật sản phẩm mới thành công", null));
        }else if(Objects.equals(check, "false01")){
            return ResponseEntity.badRequest().body(new ResponseObject("error", "Đã tồn tại tên sản phẩm: "+product.getName(), null));
        }else if(Objects.equals(check, "false02")){
            return ResponseEntity.badRequest().body(new ResponseObject("error", "Id sản phẩm không đúng hoặc không khớp, ID trên url= "+id+" hoặc Id trong mục sản phẩm= "+product.getId(),null));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Lỗi nội bộ", null));
    }
    // tạo category product

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable("id") Integer id) {
        productService.deleteById(id);
    }
    //copy product mới
    @PostMapping("/{id}")
    public ResponseEntity<ResponseObject> copyProduct(@PathVariable Integer id){
        String check = productService.copyProduct(id);
        if(check=="true"){
            return ResponseEntity.ok().body(new ResponseObject("success", "Copy sản phẩm mới thành công", null));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Copy sản phẩm thất bại", null));
    }
}
