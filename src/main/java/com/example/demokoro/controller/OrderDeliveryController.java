package com.example.demokoro.controller;

import com.example.demokoro.dto.OrderDeliveryDTO;
import com.example.demokoro.dto.ResponseObject;
import com.example.demokoro.models.OrderDelivery;
import com.example.demokoro.service.OrderDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order-delivery")
@CrossOrigin
public class OrderDeliveryController {
    @Autowired
    OrderDeliveryService orderDeliveryService;
//
    @GetMapping("/by-order-id/{id}")
    public ResponseEntity<ResponseObject> getByOrderId(@PathVariable Integer id){
        OrderDeliveryDTO dto = orderDeliveryService.getByOrderId(id);
        if(dto !=null){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lay order delivery by orderId thành công", dto));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Lấy order delivery by orderId thất bại", null));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getById(@PathVariable Integer id){
        OrderDeliveryDTO dto = orderDeliveryService.getById(id);
        if(dto !=null){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lay order delivery thành công", dto));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Lấy order delivery thất bại", null));
    }
}
