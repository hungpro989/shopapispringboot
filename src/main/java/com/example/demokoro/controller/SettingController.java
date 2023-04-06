package com.example.demokoro.controller;

import com.example.demokoro.dto.BusinessDTO;
import com.example.demokoro.dto.ResponseObject;
import com.example.demokoro.dto.SettingDTO;
import com.example.demokoro.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/setting")
@CrossOrigin
public class SettingController {
    @Autowired
    SettingService settingService;
    @GetMapping("/")
    public ResponseEntity<ResponseObject> getAllSetting(){
        List<SettingDTO> listDto = settingService.getAllSetting();
        if(!listDto.isEmpty()){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lay danh sach setting thanh cong", listDto));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Lay danh sach setting that bai", null));
    }
    @GetMapping("/by-key/{key}")
    public ResponseEntity<ResponseObject> findSettingByKey(@PathVariable String key){
        SettingDTO dto = settingService.findSettingByKey(key);
        if(dto != null){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lay setting by key thanh cong", dto));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Lay setting by key that bai", null));
    }
    @PutMapping("/{key}")
    public ResponseEntity<ResponseObject> updateSetting(@RequestBody SettingDTO settingDTO, @PathVariable String key){
        Boolean check = settingService.updateSetting(settingDTO, key);
        if(check){
            return ResponseEntity.ok().body(new ResponseObject("success", "Update setting by key thanh cong", check));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Update setting by key that bai", settingDTO));
    }
}
