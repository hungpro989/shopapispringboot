package com.example.demokoro.repository;

import com.example.demokoro.models.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingRepository extends JpaRepository<Setting, Integer> {
    Setting findSettingByMyKey(String s);

}
