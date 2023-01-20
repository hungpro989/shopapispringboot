package com.example.demokoro.controller;

import com.example.demokoro.dto.OrderCreateDTO;
import com.example.demokoro.dto.OrderDTO;
import com.example.demokoro.dto.ResponseObject;
import com.example.demokoro.repository.*;
import com.example.demokoro.service.OrderDetailService;
import com.example.demokoro.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderDetailService orderDetailService;

    //get all
    @GetMapping
    public ResponseEntity<ResponseObject> getAll(){
        List<OrderDTO> listDto = orderService.getAll();
        if(!listDto.isEmpty()){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lấy danh sách đơn hàng thành công", listDto));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Lấy danh sách đơn hàng thất bại", listDto));
    }
//get by business
    @GetMapping("/business/{id}")
    public ResponseEntity<ResponseObject> getOrderByBusinessId(@PathVariable  Integer id){
        List<OrderDTO> listDto = orderService.getAllByBusinessId(id);
        if(!listDto.isEmpty()){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lấy danh sách đơn hàng thành công", listDto));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Lấy danh sách đơn hàng thất bại", listDto));
    }
//get by id
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getById(@PathVariable Integer id){
        if(orderService.getById(id)!=null){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lấy đơn hàng thành công", orderService.getById(id)));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Không tìm thấy đơn hàng có id như trên", null));
    }
    //get by multiple condition
    @GetMapping("condition")
    public ResponseEntity<ResponseObject> getAllByCondition(
            @RequestParam(value = "employeeId", required = false) Integer employeeId,
            @RequestParam(value = "creatorId", required = false) Integer creatorId,
            @RequestParam(value = "businessId", required = false) Integer businessId,
            @RequestParam(value = "deliveryId", required = false) Integer deliveryId,
            @RequestParam(value = "orderStatusId", required = false) Integer orderStatusId,
            @RequestParam(value = "orderTypeId", required = false) Integer orderTypeId,
            @RequestParam(value = "orderTimeStart", required = false) String orderTimeStart,
            @RequestParam(value = "orderTimeEnd", required = false) String orderTimeEnd

    ) {
        LocalDate toDay = LocalDate.now(ZoneId.of("GMT+07:00"));
        String start = null;
        String end = null;
        if (orderTimeStart != null && orderTimeEnd != null) {
            start = orderTimeStart + " 00:00:00";
            end = orderTimeEnd + " 23:59:59";
        }else if(orderTimeStart != null && orderTimeEnd == null){
            start = orderTimeStart + " 00:00:00";
            end = toDay+" 23:59:59";
        }

        List<OrderDTO> listDto = orderService.getAllByCondition(employeeId, creatorId, businessId, deliveryId, orderStatusId, orderTypeId, start, end);
        if (!listDto.isEmpty()) {
            return ResponseEntity.ok().body(new ResponseObject("success", "Lấy danh sách đơn hàng thành công", listDto));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Lấy danh sách đơn hàng thất bại", null));
    }
    @PostMapping
    public ResponseEntity<ResponseObject> createOrder(@RequestBody OrderCreateDTO orderDTO){
        if(orderService.save(orderDTO)){
            return ResponseEntity.ok().body(new ResponseObject("success", "Tạo đơn hàng thành công", null));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Tạo đơn hàng thất bại", null));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateOrder(@RequestBody OrderCreateDTO orderDTO, @PathVariable Integer id){
        if(orderService.getById(id)==null){
            return ResponseEntity.badRequest().body(new ResponseObject("error", "Không tìm thấy Id đơn hàng tương ứng", null));
        }
        if(orderService.save(orderDTO)){
            return ResponseEntity.ok().body(new ResponseObject("success", "Cập nhật đơn hàng thành công", null));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Cập nhật đơn hàng thất bại", null));
    }
    @PostMapping("/{id}")
    public ResponseEntity<ResponseObject> updateOrderStatus(@PathVariable Integer id, @PathVariable Integer statusId){

        if(orderService.updateStatus(id, statusId)){
            return ResponseEntity.ok().body(new ResponseObject("success", "Cập nhật trạng thái đơn hàng thành công", null));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Cập nhật trạng thái đơn hàng thất bại", null));
    }
    @GetMapping("/status/{statusId}")
    public ResponseEntity<ResponseObject> getAll(@PathVariable Integer statusId){
        List<OrderDTO> listDto = orderService.getOrderByStatus(statusId);
        if(!listDto.isEmpty()){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lấy danh sách đơn hàng thành công", listDto));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Lấy danh sách đơn hàng thất bại", listDto));
    }
}
