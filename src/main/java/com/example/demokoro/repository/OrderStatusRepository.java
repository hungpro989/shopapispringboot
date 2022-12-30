package com.example.demokoro.repository;

import com.example.demokoro.models.Delivery;
import com.example.demokoro.models.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer> {
    OrderStatus findOrderStatusByName(String name);
}