package com.example.demokoro.service;

import com.example.demokoro.dto.OrderDTO;
import com.example.demokoro.dto.OrderDeliveryDTO;
import com.example.demokoro.models.Order;
import com.example.demokoro.models.OrderDelivery;
import com.example.demokoro.repository.*;
import com.example.demokoro.serviceImpl.IOrderDeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderDeliveryService implements IOrderDeliveryRepository {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderStatusRepository orderStatusRepository;
    @Autowired
    OrderDeliveryRepository orderDeliveryRepository;
    @Autowired
    DeliveryRepository deliveryRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderService orderService;
    @Autowired
    private OrderTypeRepository orderTypeRepository;
    @Autowired
    private BusinessRepository businessRepository;
    @Override
    public List<OrderDelivery> getAll() {
        List<OrderDelivery> list = orderDeliveryRepository.findAll();
        return list;
    }

    @Override
    public OrderDeliveryDTO getById(Integer id) {
        OrderDeliveryDTO dto = new OrderDeliveryDTO();
        OrderDelivery o = orderDeliveryRepository.findById(id).orElse(null);
        if (o !=null) {
            dto = new OrderDeliveryDTO(o);
            return dto;
        }
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        try{
            orderDeliveryRepository.deleteById(id);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean save(OrderDeliveryDTO dto) {
        try{
            //lưu response delivery gửi về
            OrderDelivery orderDelivery = new OrderDelivery(dto);
            orderDelivery.setDelivery(deliveryRepository.findById(dto.getDeliveryId()).orElse(null));
            orderDelivery.setOrder(orderRepository.findById(dto.getOrderId()).orElse(null));
            if(checkExistCodeDelivery(dto.getCodeDelivery())){
                orderDeliveryRepository.save(orderDelivery);
            }
            //đổi trạng thái đơn hàng và đơn vị vận chuyển
            OrderDTO orderDto = orderService.getById(dto.getOrderId());
            Order o = new Order(orderDto);
            o.setDelivery(deliveryRepository.findById(dto.getDeliveryId()).orElse(null));//đơn vị vận chuyển
            o.setOrderStatus(orderStatusRepository.findById(3).orElse(null)); //trạng thái đơn hàng
            o.setOrderType(orderTypeRepository.findById(orderDto.getOrderTypeDTO().getId()).orElse(null));// kiểu đơn
            o.setBusiness(businessRepository.findById(orderDto.getBusinessDTO().getId()).orElse(null)); // business
            o.setUser(userRepository.findById(orderDto.getUserOrderDTO().getId()).orElse(null));//đơn của nhân viên A
            o.setUser1(userRepository.findById(orderDto.getEmployeeCreatorOrderDTO().getId()).orElse(null));//người tạo đơn của nhân viên A
            o.setCustomer(customerRepository.findCustomerByPhone(orderDto.getPhone()));//người tạo đơn của nhân viên A
            orderRepository.save(o);
            return  true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean checkExistCodeDelivery(String s) {
        return orderDeliveryRepository.findOrderDeliveryByCodeDelivery(s)== null;
    }

    @Override
    public OrderDeliveryDTO getByOrderId(Integer id) {
        OrderDeliveryDTO dto = new OrderDeliveryDTO();
        OrderDelivery o = orderDeliveryRepository.findOrderDeliveryByOrderId(id);
        if (o !=null) {
            dto = new OrderDeliveryDTO(o);
            return dto;
        }
        return null;
    }
}
