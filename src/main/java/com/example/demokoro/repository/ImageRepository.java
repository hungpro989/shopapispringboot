package com.example.demokoro.repository;

import com.example.demokoro.models.Image;
import com.example.demokoro.models.OrderDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

}
