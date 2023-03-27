package com.example.demokoro.dto;

import com.example.demokoro.models.Business;
import com.example.demokoro.models.Source;
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
    private  String pageId;
    private SourceDTO sourceDTO;

    public BusinessDTO(Business b){
        this.id=b.getId();
        this.codeName=b.getCodeName();
        this.name=b.getName();
        this.address=b.getAddress();
        this.phone=b.getPhone();
        this.status=b.isStatus();
        this.pageId=b.getPageId();
        Source source = b.getSource();
        if (source!=null){
            this.sourceDTO=new SourceDTO(source);
        }

    }
}
