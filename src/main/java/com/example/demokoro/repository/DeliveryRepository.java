package com.example.demokoro.repository;

import com.example.demokoro.models.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
    Delivery findDeliveryByCodeName(String codeName);
}
