package com.example.demokoro.repository;

import com.example.demokoro.models.OrderTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderTagRepository extends JpaRepository<OrderTag, Integer> {
    void deleteOrderTagByOrderId(Integer orderId);
    List<OrderTag> findOrderTagByTagId(Integer id);
}
