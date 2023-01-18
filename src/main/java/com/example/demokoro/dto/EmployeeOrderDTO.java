package com.example.demokoro.dto;

import com.example.demokoro.models.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeOrderDTO {
    private Integer id;
    private String fullName;
//    private String address;
//    private String phone;
//    private String email;
//    private String image;
//    private String description;
//    private Date birthday;
//    private boolean status;

    public EmployeeOrderDTO(Employee b){
        this.id=b.getId();
        this.fullName=b.getFullName();
//        this.address=b.getAddress();
//        this.phone=b.getPhone();
//        this.email=b.getEmail();
//        this.image=b.getImage();
//        this.description=b.getDescription();
//        this.birthday=b.getBirthday();
//        this.status=b.isStatus();
    }
}
