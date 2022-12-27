package com.example.demokoro.controller;

import com.example.demokoro.dto.DeliveryDTO;
import com.example.demokoro.dto.OrderStatusDTO;
import com.example.demokoro.dto.ResponseObject;
import com.example.demokoro.models.Delivery;
import com.example.demokoro.models.OrderStatus;
import com.example.demokoro.service.DeliveryService;
import com.example.demokoro.service.OrderStatusService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orderstatus")
public class OrderStatusController {
    @Autowired
    OrderStatusService orderStatusService;
    @GetMapping
    public ResponseEntity<ResponseObject> getAll(){
        List<OrderStatusDTO> listDto = orderStatusService.getAll();
        if(!listDto.isEmpty()){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lay danh sach trang thai don hang thanh cong", listDto));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Lay danh sach trang thai don hang that bai", null));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getById(@PathVariable Integer id){
        if(orderStatusService.checkExistId(id)){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lấy trang thai don hang thành công", orderStatusService.getById(id)));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Không tìm thấy trang thai don hang có id như trên", null));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> delete(@PathVariable("id") Integer id){
        if(orderStatusService.checkExistId(id)){
            orderStatusService.deleteById(id);
            return ResponseEntity.ok().body(new ResponseObject("success", "Xoá trang thai don hang thành công", null));
        };
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Id trang thai don hang không tồn tại", null));
    }
    @PostMapping
    public ResponseEntity<ResponseObject> create(@RequestBody OrderStatusDTO orderStatus){
        OrderStatus b = new OrderStatus(orderStatus);
        if(!orderStatusService.checkExistName(orderStatus.getName())){
            try{
                orderStatusService.save(b);
                return ResponseEntity.ok().body(new ResponseObject("success", "Tạo trang thai don hang mới thành công", orderStatus));
            }catch (Exception e) {
                return ResponseEntity.badRequest().body(new ResponseObject("error", "Tạo trang thai don hang thất bại1", null));
            }
        }else{
            return ResponseEntity.badRequest().body(new ResponseObject("error", "Trang thai don hang đã tồn tại", null));
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> update(@RequestBody OrderStatus orderStatus, @PathVariable Integer id){
        OrderStatusDTO dto = orderStatusService.getById(id);
        if(dto != null){
            if(!orderStatusService.checkExistName(orderStatus.getName())) {
                if (orderStatusService.save(orderStatus)) {
                    return ResponseEntity.ok().body(new ResponseObject("success", "Cap nhat trang thai don hang thanh cong", orderStatus));
                }
                return ResponseEntity.badRequest().body(new ResponseObject("error", "Cap nhat trang thai don hang that bai", null));
            }else if(orderStatus.getName().equalsIgnoreCase(dto.getName())) {
                if (orderStatusService.save(orderStatus)) {
                    return ResponseEntity.ok().body(new ResponseObject("success", "Cap nhat trang thai don hang thanh cong", orderStatus));
                }
                return ResponseEntity.badRequest().body(new ResponseObject("error", "Cap nhat trang thai don hang that bai", null));
            }else {
                return ResponseEntity.badRequest().body(new ResponseObject("error", "Ten trang thai don hang đã tồn tại", null));
            }
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Trang thai don hang không tồn tại", null));
    }
}
