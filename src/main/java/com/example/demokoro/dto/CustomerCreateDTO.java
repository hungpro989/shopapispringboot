package com.example.demokoro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCreateDTO {
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

    private boolean status;

    private float height;

    private float weight;
    private String province;
    private String district;
    private String ward;
}
