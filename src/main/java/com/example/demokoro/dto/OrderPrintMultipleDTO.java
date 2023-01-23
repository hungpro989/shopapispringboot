package com.example.demokoro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderPrintMultipleDTO {
    private Integer deliveryId;
    private ArrayList listOrder;
}
