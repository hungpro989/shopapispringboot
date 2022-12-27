package com.example.demokoro.controller;

import com.example.demokoro.dto.BusinessDTO;
import com.example.demokoro.dto.DeliveryDTO;
import com.example.demokoro.dto.ResponseObject;
import com.example.demokoro.models.Business;
import com.example.demokoro.models.Delivery;
import com.example.demokoro.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/delivery")
public class DeliveryController {
    @Autowired
    DeliveryService deliveryService;
    @GetMapping
    public ResponseEntity<ResponseObject> getAllProduct(){
        List<DeliveryDTO> listDto = deliveryService.getAll();
        if(!listDto.isEmpty()){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lay danh sach delivery thanh cong", listDto));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Lay danh sach delivery that bai", null));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getById(@PathVariable Integer id){
        if(deliveryService.checkExistId(id)){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lấy sản phẩm thành công", deliveryService.getById(id)));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Không tìm thấy sản phẩm có id như trên", null));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> delete(@PathVariable("id") Integer id){
        if(deliveryService.checkExistId(id)){
            deliveryService.deleteById(id);
            return ResponseEntity.ok().body(new ResponseObject("success", "Xoá business thành công", null));
        };
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Id business không tồn tại", null));
    }
    @PostMapping
    public ResponseEntity<ResponseObject> create(@RequestBody DeliveryDTO delivery){
        Delivery b = new Delivery(delivery);
        if(!deliveryService.checkExistName(delivery.getCodeName())){
            try{
                deliveryService.save(b);
                return ResponseEntity.ok().body(new ResponseObject("success", "Tạo delivery mới thành công", delivery));
            }catch (Exception e) {
                return ResponseEntity.badRequest().body(new ResponseObject("error", "Tạo delivery thất bại1", null));
            }
        }else{
            return ResponseEntity.badRequest().body(new ResponseObject("error", "Tên mã của delivery đã tồn tại", null));
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> update(@RequestBody Delivery delivery, @PathVariable Integer id){
        DeliveryDTO dto = deliveryService.getById(id);
        if(dto != null){
            if(!deliveryService.checkExistName(delivery.getCodeName())) {
                if (deliveryService.save(delivery)) {
                    return ResponseEntity.ok().body(new ResponseObject("success", "Cap nhat delivery thanh cong", delivery));
                }
                return ResponseEntity.badRequest().body(new ResponseObject("error", "Cap nhat delivery that bai", null));
            }else if(delivery.getCodeName().equalsIgnoreCase(dto.getCodeName())) {
                if (deliveryService.save(delivery)) {
                    return ResponseEntity.ok().body(new ResponseObject("success", "Cap nhat delivery thanh cong", delivery));
                }
                return ResponseEntity.badRequest().body(new ResponseObject("error", "Cap nhat delivery that bai", null));
            }else {
                return ResponseEntity.badRequest().body(new ResponseObject("error", "Mã delivery đã tồn tại", null));
            }
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Delivery không tồn tại", null));
    }
}
