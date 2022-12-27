package com.example.demokoro.repository;

import com.example.demokoro.models.OrderStatus;
import com.example.demokoro.models.OrderType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderTypeRepository extends JpaRepository<OrderType, Integer> {
    OrderType findOrderTypeByName(String name);
}
