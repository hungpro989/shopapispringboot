package com.example.demokoro.payload;

import lombok.Data;

@Data
public class LoginRequest {

    private String username;


    private String password;
}
