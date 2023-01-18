package com.example.demokoro.repository;

import com.example.demokoro.models.User;
import com.example.demokoro.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findOrderByBusinessId(Integer id);

//    @Query("select e from Order e where (:employeeId is null or e.employee = :employeeId) and (:creatorId is null or e.employee1 = :creatorId) and (:businessId is null or e.business = :businessId) and (:deliveryId is null or e.delivery = :deliveryId) and (:orderStatusId is null or e.orderStatus = :orderStatusId) and (:orderTypeId is null or e.orderType = :orderTypeId)")
//    List<Order> filterOrderByCondition(Employee employeeId, Optional<Employee> creatorId, Optional<Business> businessId, Optional<Delivery> deliveryId, Optional<OrderStatus> orderStatusId, Optional<OrderType> orderTypeId);
    @Query(value = "select * from orders o where (:userId is null or o.user_id = :userId) and (:creatorId is null or o.creator_id = :creatorId) and (:businessId is null or o.business_id = :businessId) and (:deliveryId is null or o.delivery_id = :deliveryId) and (:orderStatusId is null or o.order_status_id = :orderStatusId) and (:orderTypeId is null or o.order_type_id = :orderTypeId) and (:orderTimeStart is null or :orderTimeEnd is null or o.order_time BETWEEN :orderTimeStart AND :orderTimeEnd)",nativeQuery = true)
    List<Order> filterOrderByCondition(Integer userId, Integer creatorId, Integer businessId, Integer deliveryId, Integer orderStatusId, Integer orderTypeId, String orderTimeStart, String orderTimeEnd);

    @Query(value = "select o from Order o where (o.user is null or o.user = :userId)")
    List<Order> filterOrderByCondition1(User userId);
}
