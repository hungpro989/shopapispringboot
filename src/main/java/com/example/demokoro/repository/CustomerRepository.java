package com.example.demokoro.repository;

import com.example.demokoro.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findCustomerByEmail(String email);

    Customer findCustomerByPhone(String phone);

    Customer findCustomerByUsername(String username);
}
