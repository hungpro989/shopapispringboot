package com.example.demokoro.models;

import com.example.demokoro.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

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

    @OneToMany(mappedBy = "user")
    private List<Order> order;



    public User(UserDTO dto){
        this.id = dto.getId();
        this.username = dto.getUsername();
        this.password = dto.getPassword();
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
