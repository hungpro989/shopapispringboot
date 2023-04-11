package com.example.demokoro.dto;

import com.example.demokoro.models.Customer;
import com.example.demokoro.models.CustomerAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerViewDTO {
    private Integer id;

    private String username;

    private String password;

    private String fullName;

    private String address;

    private String phone;

    private String email;

    private String image;

    private String description;

    private Date birthday;

    private Boolean status;

    private float height;

    private float weight;

    private String province;
    private String district;
    private String ward;

    private List<CustomerAddressDTO> customerAddressDTO;

    public CustomerViewDTO(Customer c) {
        this.id=c.getId();
        this.username=c.getUsername();
        this.password=c.getPassword();
        this.fullName=c.getFullName();
        this.phone= c.getPhone();
        this.address= c.getAddress();
        this.email= c.getEmail();
        this.image=c.getImage();
        this.description=c.getDescription();
        this.height=c.getHeight();
        this.weight=c.getWeight();
        this.birthday=c.getBirthday();
        this.status= c.getStatus();
        //lấy customer address
        //lay danh sach order detail
        List<CustomerAddress> customerAddresses = c.getCustomerAddresses();//lay ra
        List<CustomerAddressDTO> customerAddressDTO = new ArrayList<>();//tao mang moi
        customerAddresses.forEach(p-> {
            customerAddressDTO.add(new CustomerAddressDTO(p));
        });
        this.customerAddressDTO = customerAddressDTO;
    this.province=c.getProvince();
    this.district=c.getDistrict();
    this.ward=c.getWard();
    }
}
