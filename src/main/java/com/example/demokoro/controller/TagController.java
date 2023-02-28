package com.example.demokoro.controller;

import com.example.demokoro.dto.ResponseObject;
import com.example.demokoro.dto.TagDTO;
import com.example.demokoro.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tag")
@CrossOrigin
public class TagController {
    @Autowired
    TagService tagService;
    @GetMapping
    public ResponseEntity<ResponseObject> getAll(){
        List<TagDTO> listDto = tagService.getAll();
        if(!listDto.isEmpty()){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lấy danh sách TAG đơn hàng thành công", listDto));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Lấy danh sách TAG đơn hàng thất bại", null));
    }

}
