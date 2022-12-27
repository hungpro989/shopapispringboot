package com.example.demokoro.repository;

import com.example.demokoro.models.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Integer> {
    Business findBusinessByCodeName(String name);
}
