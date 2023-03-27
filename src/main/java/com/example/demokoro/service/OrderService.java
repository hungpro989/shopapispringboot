package com.example.demokoro.service;

import com.example.demokoro.dto.OrderCreateDTO;
import com.example.demokoro.dto.OrderDTO;
import com.example.demokoro.dto.OrderPrintMultipleDTO;
import com.example.demokoro.dto.ProductCreateDTO;
import com.example.demokoro.models.*;
import com.example.demokoro.repository.*;
import com.example.demokoro.serviceImpl.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.demokoro.common.common.generateString;

@Service
public class OrderService implements IOrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderTagService orderTagService;
    @Autowired
    private UserRepository userRepository;
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

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    private TagRepository tagRepository;

    @Override
    public List<OrderDTO> getAll() {
        List<OrderDTO> listDto = new ArrayList<>();
        List<Order> list = orderRepository.findAll(Sort.by(Sort.Direction.DESC, "orderTime"));
        for (Order var : list) {
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
    public List<OrderDTO> getAllByCondition(Integer userId, Integer creatorId, Integer businessId, Integer deliveryId, Integer orderStatusId, Integer orderTypeId, String orderTimeStart, String orderTimeEnd) {
        List<OrderDTO> listDto = new ArrayList<>();
        List<Order> list = orderRepository.filterOrderByCondition(userId, creatorId, businessId, deliveryId, orderStatusId, orderTypeId, orderTimeStart, orderTimeEnd);
        for (Order var : list) {
            listDto.add((new OrderDTO(var)));
        }
        return listDto;
    }

    @Override
    public List<OrderDTO> getAllByBusinessId(Integer id) {
        List<OrderDTO> listDto = new ArrayList<>();
        List<Order> list = orderRepository.findOrderByBusinessIdOrderByOrderTimeDesc(id);
        for (Order var : list) {
            listDto.add((new OrderDTO(var)));
        }
        return listDto;
    }

    @Override
    public OrderDTO getById(Integer id) {
        OrderDTO dto = new OrderDTO();
        if (orderRepository.findById(id).isPresent()) {
            Order order = orderRepository.findById(id).get();
            dto = new OrderDTO(order);
            return dto;
        }
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        try {
            orderRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean save(OrderCreateDTO orderDTO) {
        //nhận dữ liệu từ DTO và ép vào object Order
        Order o = new Order(orderDTO);
        Customer customer = new Customer();
        if (customerRepository.findCustomerByPhone(orderDTO.getPhone()) == null) {
            customer.setFullName(orderDTO.getName());
            customer.setPhone(orderDTO.getPhone());
            customer.setAddress(orderDTO.getAddress());
            customerRepository.save(customer);
        } else {
            customer = customerRepository.findCustomerByPhone(orderDTO.getPhone());
        }
        //liên kết các bảng 1-n
        if(orderDTO.getStatusId()!=null){
            o.setOrderStatus(orderStatusRepository.findById(orderDTO.getStatusId()).orElse(null)); //trạng thái đơn hàng
        }
        if(orderDTO.getTypeId()!=null){
            o.setOrderType(orderTypeRepository.findById(orderDTO.getTypeId()).orElse(null));// kiểu đơn
        }
        if(orderDTO.getBusinessId()!=null){
            o.setBusiness(businessRepository.findById(orderDTO.getBusinessId()).orElse(null)); // business
        }
        if(orderDTO.getDeliveryId()!=null){
            o.setDelivery(deliveryRepository.findById(orderDTO.getDeliveryId()).orElse(null));//đơn vị vận chuyển
        }
        if(orderDTO.getUserId()!=null){
            o.setUser(userRepository.findById(orderDTO.getUserId()).orElse(null));//đơn của nhân viên A
        }
        if(orderDTO.getCreatorId()!=null){
            o.setUser1(userRepository.findById(orderDTO.getCreatorId()).orElse(null));//người tạo đơn của nhân viên A
        }
        if(orderDTO.getId()==null){
            //tao moi
            o.setBillCode(generateString(o.getBusiness().getCodeName().trim())); //taoj mã bill code
        }else {
            o.setBillCode(getById(orderDTO.getId()).getBillCode());
        }

        o.setCustomer(customer);
        if (o.getOrderTime() == null) {
            o.setOrderTime(new Date());
            o.setCustomer(customer);
        }
        orderRepository.save(o);
        createOrderTag(orderDTO, o);
        createProductDetail(orderDTO, o);
        return true;
    }
    public void createProductDetail(@RequestBody OrderCreateDTO orderDTO, Order o){
        orderDTO.getOrderDetailDTO().forEach(var -> {
            ProductDetail productDetail = productDetailRepository.findById(var.getProDeId()).orElse(null);
            if (productDetail != null) {
                OrderDetail orderDetail = new OrderDetail(var);
                orderDetail.setOrders(o);
                orderDetail.setProductDetail(productDetail);
                orderDetail.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                orderDetailService.save(orderDetail);
            }
        });
    }
    public void createOrderTag(@RequestBody OrderCreateDTO orderCreateDTO, Order p) {
        if(orderCreateDTO.getOrderTag()!= null){
            if(orderTagService.deleteOrderTagByOrderId(p.getId())){
                orderCreateDTO.getOrderTag().forEach(var -> {
                    Tag tag = new Tag();
                    tag = tagRepository.findById(var.getTagId()).orElse(null);
                    if (tag!=null){
                        OrderTag orderTag = new OrderTag();
                        orderTag.setOrder(p);
                        orderTag.setTag(tag);
                        orderTagService.save(orderTag);
                    }
                });
            }else{
                System.out.println("NG");
            }

        }
    }
    @Override
    public boolean updateStatus(Integer id, Integer statusId) {
        Order o = orderRepository.findById(id).orElse(null);
        if (o != null) {
            o.setOrderStatus(orderStatusRepository.findById(statusId).orElse(null));
            orderRepository.save(o);
            return true;
        }
        return false;
    }
    @Override
    public boolean updateDelivery(Integer id, Integer deliveryId) {
        Order o = orderRepository.findById(id).orElse(null);
        if (o != null) {
            o.setDelivery(deliveryRepository.findById(deliveryId).orElse(null));
            orderRepository.save(o);
            return true;
        }
        return false;
    }
    @Override
    public List<OrderDTO> getOrderByStatus(Integer id) {
        List<OrderDTO> listDto = new ArrayList<>();
        List<Order> list = orderRepository.findOrderByOrderStatusId(id);
        for (Order var : list) {
            listDto.add(new OrderDTO(var));
        }
        return listDto;
    }
    @Override
    public OrderDTO printBill(Integer id, Integer deliveryId) {
        if(updateDelivery(id, deliveryId)){
            if(updateStatus(id, 3)){
                Order o = orderRepository.findById(id).orElse(null);
                return new OrderDTO(o);
            }
            return null;
        }
        return null;
    }
    @Override
    public List<OrderDTO> printMultipleBill(OrderPrintMultipleDTO orderPrintMultipleDTO) {
        List<OrderDTO> listDto = new ArrayList<>();
        orderPrintMultipleDTO.getListOrder().forEach(var->{
            OrderDTO orderDTO = printBill((Integer) var, orderPrintMultipleDTO.getDeliveryId());
            listDto.add(orderDTO);
        });
        return listDto;
    }
    @Override
    public OrderDTO getOrderByBillCode(String billCode) {
        Order o = orderRepository.findOrderByBillCode(billCode);
        if (o!=null) {
            return new OrderDTO(o);
        }
        return null;
    }
}
