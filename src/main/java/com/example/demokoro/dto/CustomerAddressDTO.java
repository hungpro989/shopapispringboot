package com.example.demokoro.dto;

import com.example.demokoro.models.CustomerAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAddressDTO {
    private Integer id;
    private Integer customerId;
    private String address;
    private String phone;
    private String name;
    private Boolean status;

    public CustomerAddressDTO(CustomerAddress p) {
        this.id=p.getId();
        this.address=p.getAddress();
        this.phone=p.getPhone();
        this.name=p.getName();
        this.status=p.getStatus();
        this.customerId=p.getCustomer().getId();
    }
}
