package com.example.demokoro.controller;

import com.example.demokoro.dto.AddressByDistrictDTO;
import com.example.demokoro.dto.AddressByProvinceDTO;
import com.example.demokoro.dto.AddressDTO;
import com.example.demokoro.dto.ResponseObject;
import com.example.demokoro.models.Address;
import com.example.demokoro.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/address")
public class AddressController {
    @Autowired
    AddressService addressService;
    @GetMapping
    public ResponseEntity<ResponseObject>getAllAddress(){
        List<AddressDTO> listDto = addressService.getAllAddress();
        if(!listDto.isEmpty()){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lấy danh sách địa chỉ thành công",listDto));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Lấy danh sách địa chỉ thất bại", null));

    }
    @GetMapping("/province")
    public ResponseEntity<ResponseObject>getAllProvince(){
        List<String> listAddress = addressService.getAllProvince();
        if(!listAddress.isEmpty()){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lấy danh sách tỉnh thành thành thành công",listAddress));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Lấy danh sách tỉnh thành thất bại", null));
    }
    @GetMapping("/district")
    public ResponseEntity<ResponseObject>getAllDistrict(@RequestParam String province){
        List<String> list = addressService.getAllDistrict(province);
        if(!list.isEmpty()){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lấy danh sách quận huyện thành thành công",list));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Lấy danh sách quận huyện thành thất bại", null));
    }
    @GetMapping("/ward")
    public ResponseEntity<ResponseObject>getAllWard(@RequestParam String district){
        System.out.println(district);
        List<String> list = addressService.getAllWard(district);
        if(!list.isEmpty()){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lấy danh sách phường xã thành thành công",list));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Lấy danh sách phường xã thành thất bại", null));
    }
}
