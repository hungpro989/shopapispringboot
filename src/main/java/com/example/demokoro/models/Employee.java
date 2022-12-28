package com.example.demokoro.models;

import com.example.demokoro.dto.BusinessDTO;
import com.example.demokoro.dto.EmployeeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "image")
    private String image;

    @Column(name = "description")
    private String description;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "status")
    private boolean status;

    public Employee(EmployeeDTO dto){
        this.id = dto.getId();
        this.fullName=dto.getFullName();
        this.address=dto.getAddress();
        this.phone=dto.getPhone();
        this.email=dto.getEmail();
        this.address=dto.getAddress();
        this.image=dto.getImage();
        this.description=dto.getDescription();
        this.birthday=dto.getBirthday();
        this.status=dto.isStatus();
    }
}
