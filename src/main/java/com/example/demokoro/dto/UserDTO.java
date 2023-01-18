package com.example.demokoro.dto;

import com.example.demokoro.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
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

    public UserDTO(User b){
        this.id=b.getId();
        this.username = b.getUsername();
        this.password = b.getPassword();
        this.fullName=b.getFullName();
        this.address=b.getAddress();
        this.phone=b.getPhone();
        this.email=b.getEmail();
        this.image=b.getImage();
        this.description=b.getDescription();
        this.birthday=b.getBirthday();
        this.status=b.isStatus();
    }
}
