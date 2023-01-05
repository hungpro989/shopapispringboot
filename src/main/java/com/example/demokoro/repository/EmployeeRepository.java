package com.example.demokoro.repository;

import com.example.demokoro.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findEmployeeByPhone(String phone);
    Employee findEmployeeByEmail(String email);
    @Query("select e from Employee e where e.id <> :id and e.phone= :phone or e.email= :email")
    List<Employee> getEmployeeWhereNotId(Integer id, String email, String phone);
}
