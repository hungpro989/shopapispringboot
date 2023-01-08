package com.example.demokoro.service;

import com.example.demokoro.dto.OrderCreateDTO;
import com.example.demokoro.dto.OrderDTO;
import com.example.demokoro.models.*;
import com.example.demokoro.common.common;
import com.example.demokoro.repository.*;
import com.example.demokoro.serviceImpl.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.demokoro.common.common.generateString;

@Service
public class OrderService implements IOrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private BusinessRepository businessRepository;
    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private OrderStatusRepository orderStatusRepository;
    @Autowired
    private OrderTypeRepository orderTypeRepository;
    @Autowired
    OrderDetailService orderDetailService;
    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Override
    public List<OrderDTO> getAll() {
        List<OrderDTO> listDto = new ArrayList<>();
        List<Order> list = orderRepository.findAll();
        for(Order var: list){
            listDto.add((new OrderDTO(var)));
        }
        return listDto;
    }

//    @Override
//    public List<OrderDTO> getAllByCondition(Integer employeeId, Integer creatorId, Integer businessId, Integer deliveryId, Integer orderStatusId, Integer orderTypeId) {
//        List<OrderDTO> listDto = new ArrayList<>();
//        Employee employee = employeeService.getById1(employeeId);
//        Optional<Employee> creator =employeeRepository.findById(creatorId);
//        Optional<Business> business = businessRepository.findById(businessId);
//        Optional<Delivery> delivery = deliveryRepository.findById(deliveryId);
//        Optional<OrderStatus> orderStatus =orderStatusRepository.findById(orderStatusId);
//        Optional<OrderType> orderType = orderTypeRepository.findById(orderTypeId);
//
//        List<Order> list = orderRepository.filterOrderByCondition( employee, creator,  business,  delivery,  orderStatus,  orderType);
//        for(Order var: list){
//            listDto.add((new OrderDTO(var)));
//        }
//        return listDto;
//    }
    @Override
    public List<OrderDTO> getAllByCondition(Integer employeeId, Integer creatorId, Integer businessId, Integer deliveryId, Integer orderStatusId, Integer orderTypeId, String orderTimeStart, String orderTimeEnd) {
        List<OrderDTO> listDto = new ArrayList<>();
        List<Order> list = orderRepository.filterOrderByCondition( employeeId, creatorId, businessId ,  deliveryId,  orderStatusId,  orderTypeId,orderTimeStart, orderTimeEnd);
        for(Order var: list){
            listDto.add((new OrderDTO(var)));
        }
        return listDto;
    }

    @Override
    public List<OrderDTO> getAllByBusinessId(Integer id) {
        List<OrderDTO> listDto = new ArrayList<>();
        List<Order> list = orderRepository.findOrderByBusinessId(id);
        for(Order var: list){
            listDto.add((new OrderDTO(var)));
        }
        return listDto;
    }

    @Override
    public OrderDTO getById(Integer id) {
        OrderDTO dto = new OrderDTO();
        if(orderRepository.findById(id).isPresent()){
            Order order = orderRepository.findById(id).get();
            dto = new OrderDTO(order);
            return dto;
        }
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        try{
            orderRepository.deleteById(id);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean save(OrderCreateDTO orderDTO) {
            //nhận dữ liệu từ DTO và ép vào object Order
            Order o = new Order(orderDTO);
            //liên kết các bảng
            o.setOrderStatus(orderStatusRepository.findById(orderDTO.getStatusId()).orElse(null)); //trạng thái đơn hàng
            o.setOrderType(orderTypeRepository.findById(orderDTO.getTypeId()).orElse(null));// kiểu đơn
            o.setBusiness(businessRepository.findById(orderDTO.getBusinessId()).orElse(null)); // business
            o.setDelivery(deliveryRepository.findById(orderDTO.getDeliveryId()).orElse(null));//đơn vị vận chuyển
            o.setEmployee(employeeRepository.findById(orderDTO.getEmployeeId()).orElse(null));//đơn của nhân viên A
            o.setEmployee1(employeeRepository.findById(orderDTO.getCreatorId()).orElse(null));//người tạo đơn của nhân viên A
            o.setBillCode(generateString(o.getBusiness().getCodeName().trim()));
            if(o.getOrderTime()==null){
                o.setOrderTime(new Date());
            }
        orderRepository.save(o);
        orderDTO.getOrderDetailDTO().forEach(var ->{
            Integer productDetailId = var.getProDeId();
            ProductDetail productDetail = productDetailRepository.findById(productDetailId).orElse(null);
            if(productDetail!=null){
                OrderDetail orderDetail = new OrderDetail(var);
                orderDetail.setOrders(o);
                orderDetail.setProductDetail(productDetail);
                orderDetail.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                orderDetailService.save(orderDetail);
            }
        });
//        orderDTO.getOrderDetailDTO().forEach(var->{
//            OrderDetail orderDetail = new OrderDetail(var);
//        });
        return  true;
    }
}
