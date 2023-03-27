package com.example.demokoro.repository;

import com.example.demokoro.models.Source;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SourceRepository  extends JpaRepository<Source, Integer> {
}
