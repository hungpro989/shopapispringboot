package com.example.demokoro.controller;

import com.example.demokoro.dto.EmployeeDTO;
import com.example.demokoro.dto.OrderDTO;
import com.example.demokoro.dto.ResponseObject;
import com.example.demokoro.models.Employee;
import com.example.demokoro.service.EmployeeService;
import com.example.demokoro.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    @Autowired
    OrderService orderService;
    @GetMapping
    public ResponseEntity<ResponseObject> getAll(){
        List<OrderDTO> listDto = orderService.getAll();
        if(!listDto.isEmpty()){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lấy danh sách đơn hàng thành công", listDto));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Lấy danh sách đơn hàng thất bại", listDto));
    }

}
