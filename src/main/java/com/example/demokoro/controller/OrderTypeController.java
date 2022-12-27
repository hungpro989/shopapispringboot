package com.example.demokoro.controller;

import com.example.demokoro.dto.OrderTypeDTO;
import com.example.demokoro.dto.ResponseObject;
import com.example.demokoro.models.OrderType;
import com.example.demokoro.service.OrderTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ordertype")
public class OrderTypeController {
    @Autowired
    OrderTypeService orderTypeService;
    @GetMapping
    public ResponseEntity<ResponseObject> getAll(){
        List<OrderTypeDTO> listDto = orderTypeService.getAll();
        if(!listDto.isEmpty()){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lấy danh sách kiểu đơn hàng thành công", listDto));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Lấy danh sách kiểu đơn hàng thất bại", null));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getById(@PathVariable Integer id){
        if(orderTypeService.checkExistId(id)){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lấy kiểu đơn hàng thành công", orderTypeService.getById(id)));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Không tìm thấy kiểu đơn hàng có id như trên", null));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> delete(@PathVariable("id") Integer id){
        if(orderTypeService.checkExistId(id)){
            orderTypeService.deleteById(id);
            return ResponseEntity.ok().body(new ResponseObject("success", "Xoá kiểu đơn thành công", null));
        };
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Id kiểu đơn hàng không tồn tại", null));
    }
    @PostMapping
    public ResponseEntity<ResponseObject> create(@RequestBody OrderTypeDTO orderType){
        OrderType b = new OrderType(orderType);
        if(!orderTypeService.checkExistName(orderType.getName())){
            try{
                orderTypeService.save(b);
                return ResponseEntity.ok().body(new ResponseObject("success", "Tạo kiểu đơn hàng mới thành công", orderType));
            }catch (Exception e) {
                return ResponseEntity.badRequest().body(new ResponseObject("error", "Tạo kiểu đơn hàng thất bại1", null));
            }
        }else{
            return ResponseEntity.badRequest().body(new ResponseObject("error", "Kiểu đơn hàng đã tồn tại", null));
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> update(@RequestBody OrderType orderType, @PathVariable Integer id){
        OrderTypeDTO dto = orderTypeService.getById(id);
        if(dto != null){
            if(!orderTypeService.checkExistName(orderType.getName())) {
                if (orderTypeService.save(orderType)) {
                    return ResponseEntity.ok().body(new ResponseObject("success", "Cập nhật kiểu đơn thành công", orderType));
                }
                return ResponseEntity.badRequest().body(new ResponseObject("error", "Cập nhật kiểu đơn hàng thất bại", null));
            }else if(orderType.getName().equalsIgnoreCase(dto.getName())) {
                if (orderTypeService.save(orderType)) {
                    return ResponseEntity.ok().body(new ResponseObject("success", "Cập nhật kiểu đơn thành công", orderType));
                }
                return ResponseEntity.badRequest().body(new ResponseObject("error", "Cập nhật kiểu đơn hàng thất bại", null));
            }else {
                return ResponseEntity.badRequest().body(new ResponseObject("error", "Tên kiểu đơn hàng đã tồn tại", null));
            }
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Kiểu đơn hàng không tồn tại", null));
    }
}
