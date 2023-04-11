package com.example.demokoro.controller;

import com.example.demokoro.dto.ResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AdminController {
    @GetMapping("/")
    public ResponseEntity<ResponseObject> homeServer(){
        return ResponseEntity.ok().body(new ResponseObject("success", "Đây là trang chủ server", null));
    }
}
