package com.example.demokoro.repository;

import com.example.demokoro.models.OrderDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDeliveryRepository extends JpaRepository<OrderDelivery, Integer> {
    OrderDelivery findOrderDeliveryByCodeDelivery (String s);
    OrderDelivery findOrderDeliveryByOrderId(Integer id);
}
