package com.example.demokoro.dto;

import com.example.demokoro.models.Business;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessDTO {
    private Integer id;
    private String codeName;
    private String name;
    private String address;
    private String phone;
    private boolean status;

    public BusinessDTO(Business b){
        this.id=b.getId();
        this.codeName=b.getCodeName();
        this.name=b.getName();
        this.address=b.getAddress();
        this.phone=b.getPhone();
        this.status=b.isStatus();
    }
}
