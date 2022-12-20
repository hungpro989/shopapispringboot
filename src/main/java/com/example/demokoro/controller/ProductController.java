package com.example.demokoro.controller;

import com.example.demokoro.dto.ProductDTOAdmin;
import com.example.demokoro.dto.ResponseObject;
import com.example.demokoro.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1")
@RestController
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping("/products")
    public ResponseEntity<ResponseObject> getAllProduct(){
        List<ProductDTOAdmin> listDto = productService.getAllProduct();
        if(!listDto.isEmpty()){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lay danh sach san pham thanh cong", listDto));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Lay danh sach san pham that bai", null));
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<ResponseObject> getProductById(@PathVariable Integer id){
        if(productService.checkExistId(id)){
            return ResponseEntity.badRequest().body(new ResponseObject("error", "Không tìm thấy sản phẩm có id như trên", null));
        }
        return ResponseEntity.ok().body(new ResponseObject("success", "Lấy sản phẩm thành công", productService.getProducById(id)));
    }
}
