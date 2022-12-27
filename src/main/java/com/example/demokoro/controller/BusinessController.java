package com.example.demokoro.controller;

import com.example.demokoro.dto.BusinessDTO;
import com.example.demokoro.dto.ProductCreateDTO;
import com.example.demokoro.dto.ProductDTOAdmin;
import com.example.demokoro.dto.ResponseObject;
import com.example.demokoro.models.Business;
import com.example.demokoro.models.Product;
import com.example.demokoro.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/business")
public class BusinessController {
    @Autowired
    BusinessService businessService;

    @GetMapping
    public ResponseEntity<ResponseObject> getAllProduct(){
        List<BusinessDTO> listDto = businessService.getAllBusiness();
        if(!listDto.isEmpty()){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lay danh sach business thanh cong", listDto));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Lay danh sach business that bai", null));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getProductById(@PathVariable Integer id){
        if(businessService.checkExistId(id)){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lấy sản phẩm thành công", businessService.getBusinessById(id)));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Không tìm thấy sản phẩm có id như trên", null));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteBusiness(@PathVariable("id") Integer id){
        if(businessService.checkExistId(id)){
            businessService.deleteById(id);
            return ResponseEntity.ok().body(new ResponseObject("success", "Xoá business thành công", null));
        };
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Id business không tồn tại", null));
    }
    @PostMapping
    public ResponseEntity<ResponseObject> create(@RequestBody BusinessDTO business){
        Business b = new Business(business);
        if(!businessService.checkExistName(business.getCodeName())){
            try{
                businessService.save(b);
                return ResponseEntity.ok().body(new ResponseObject("success", "Tạo business mới thành công", business));
            }catch (Exception e) {
                return ResponseEntity.badRequest().body(new ResponseObject("error", "Tạo business thất bại1", null));
            }
        }else{
            return ResponseEntity.badRequest().body(new ResponseObject("error", "Tên mã của business đã tồn tại", null));
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> update(@RequestBody Business business, @PathVariable Integer id){
        BusinessDTO dto = businessService.getBusinessById(id);
        if(dto != null){
            if(!businessService.checkExistName(business.getCodeName())) {
                if (businessService.save(business)) {
                    return ResponseEntity.ok().body(new ResponseObject("success", "Cap nhat business thanh cong", business));
                }
                return ResponseEntity.badRequest().body(new ResponseObject("error", "Cap nhat business that bai", null));
            }else if(business.getCodeName().equalsIgnoreCase(dto.getCodeName())) {
                if (businessService.save(business)) {
                    return ResponseEntity.ok().body(new ResponseObject("success", "Cap nhat business thanh cong", business));
                }
                return ResponseEntity.badRequest().body(new ResponseObject("error", "Cap nhat business that bai", null));
            }else {
                return ResponseEntity.badRequest().body(new ResponseObject("error", "Mã business đã tồn tại", null));
            }
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Business không tồn tại", null));
    }
}
