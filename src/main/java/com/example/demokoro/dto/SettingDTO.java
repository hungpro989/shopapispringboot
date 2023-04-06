package com.example.demokoro.dto;

import com.example.demokoro.models.Setting;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SettingDTO {
    private Integer id;
    private String myKey;
    private String value;
    private String data;

    public SettingDTO(Setting var) {
        this.id = var.getId();
        this.myKey=var.getMyKey();
        this.value = var.getValue();
        this.data=var.getData();
    }
}
