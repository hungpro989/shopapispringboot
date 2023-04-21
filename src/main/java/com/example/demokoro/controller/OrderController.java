package com.example.demokoro.controller;

import com.example.demokoro.dto.*;
import com.example.demokoro.models.Order;
import com.example.demokoro.models.OrderDelivery;
import com.example.demokoro.service.OrderDeliveryService;
import com.example.demokoro.service.OrderDetailService;
import com.example.demokoro.service.OrderService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/orders")
@CrossOrigin
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderDetailService orderDetailService;
    @Autowired
    OrderDeliveryService orderDeliveryService;
    //get all
    @GetMapping
    public ResponseEntity<ResponseObject> getAll(){
        List<OrderDTO> listDto = orderService.getAll();
        if(!listDto.isEmpty()){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lấy danh sách đơn hàng thành công", listDto));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Lấy danh sách đơn hàng thất bại", listDto));
    }
//get by business
    @GetMapping("/business/{id}")
    public ResponseEntity<ResponseObject> getOrderByBusinessId(@PathVariable  Integer id){
        List<OrderDTO> listDto = orderService.getAllByBusinessId(id);
        if(!listDto.isEmpty()){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lấy danh sách đơn hàng thành công", listDto));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Lấy danh sách đơn hàng thất bại", listDto));
    }
//get by id
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getById(@PathVariable Integer id){
        if(orderService.getById(id)!=null){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lấy đơn hàng thành công", orderService.getById(id)));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Không tìm thấy đơn hàng có id như trên", null));
    }
    //get by multiple condition
    @GetMapping("condition")
    public ResponseEntity<ResponseObject> getAllByCondition(
            @RequestParam(value = "employeeId", required = false) Integer employeeId,
            @RequestParam(value = "creatorId", required = false) Integer creatorId,
            @RequestParam(value = "businessId", required = false) Integer businessId,
            @RequestParam(value = "deliveryId", required = false) Integer deliveryId,
            @RequestParam(value = "orderStatusId", required = false) Integer orderStatusId,
            @RequestParam(value = "orderTypeId", required = false) Integer orderTypeId,
            @RequestParam(value = "orderTimeStart", required = false) String orderTimeStart,
            @RequestParam(value = "orderTimeEnd", required = false) String orderTimeEnd

    ) {
        LocalDate toDay = LocalDate.now(ZoneId.of("GMT+07:00"));
        String start = null;
        String end = null;
        if (orderTimeStart != null && orderTimeEnd != null) {
            start = orderTimeStart + " 00:00:00";
            end = orderTimeEnd + " 23:59:59";
        }else if(orderTimeStart != null && orderTimeEnd == null){
            start = orderTimeStart + " 00:00:00";
            end = toDay+" 23:59:59";
        }

        List<OrderDTO> listDto = orderService.getAllByCondition(employeeId, creatorId, businessId, deliveryId, orderStatusId, orderTypeId, start, end);
        if (!listDto.isEmpty()) {
            return ResponseEntity.ok().body(new ResponseObject("success", "Lấy danh sách đơn hàng thành công", listDto));
        }
        return ResponseEntity.ok().body(new ResponseObject("success", "Danh sách đơn hàng rỗng", null));
    }
    @PostMapping
    public ResponseEntity<ResponseObject> createOrder(@RequestBody OrderCreateDTO orderCreateDTO){
        if(orderService.save(orderCreateDTO)){
            return ResponseEntity.ok().body(new ResponseObject("success", "Tạo đơn hàng thành công", orderCreateDTO));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Tạo đơn hàng thất bại", null));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateOrder(@RequestBody OrderCreateDTO orderDTO, @PathVariable Integer id){
        if(orderService.getById(id)==null){
            return ResponseEntity.badRequest().body(new ResponseObject("error", "Không tìm thấy Id đơn hàng tương ứng", null));
        }
        if(orderService.save(orderDTO)){
            return ResponseEntity.ok().body(new ResponseObject("success", "Cập nhật đơn hàng thành công", null));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Cập nhật đơn hàng thất bại", orderDTO));
    }
    @PutMapping("/{id}/{statusId}")
    public ResponseEntity<ResponseObject> updateOrderStatus(@PathVariable Integer id, @PathVariable Integer statusId){

        if(orderService.updateStatus(id, statusId)){
            return ResponseEntity.ok().body(new ResponseObject("success", "Cập nhật trạng thái đơn hàng thành công", null));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Cập nhật trạng thái đơn hàng thất bại", null));
    }
    @GetMapping("/status/{statusId}")
    public ResponseEntity<ResponseObject> getAll(@PathVariable Integer statusId){
        List<OrderDTO> listDto = orderService.getOrderByStatus(statusId);
        if(!listDto.isEmpty()){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lấy danh sách đơn hàng thành công", listDto));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Lấy danh sách đơn hàng thất bại", listDto));
    }
    @PostMapping("/printbill/{id}/{deliveryId}")
    public ResponseEntity<ResponseObject> printBill(@PathVariable Integer id, @PathVariable Integer deliveryId){

        if(orderService.printBill(id, deliveryId)!=null){
            return ResponseEntity.ok().body(new ResponseObject("success", "In đơn hàng thành công", orderService.printBill(id, deliveryId)));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "In đơn hàng thất bại", null));
    }
    @PostMapping("/printMultipleBill")
    public ResponseEntity<ResponseObject> printMultipleBill(@RequestBody OrderPrintMultipleDTO list){
        return ResponseEntity.ok().body(new ResponseObject("test", "Testtttt...", orderService.printMultipleBill(list)));
    }
    @DeleteMapping("/order-detail/{id}")
    public ResponseEntity<ResponseObject> deleteOrderDetail(@PathVariable Integer id){
        if(orderDetailService.deleteById(id)){
            return ResponseEntity.ok().body(new ResponseObject("success", "Xoá product detail thành công", null));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Xoá product detail thất bại", null));
    }
    @PutMapping("/save-data-delivery")
    public ResponseEntity<ResponseObject>  createOrderDelivery(@RequestBody OrderDeliveryDTO orderDeliveryDTO){
            if(orderDeliveryService.save(orderDeliveryDTO)){
                return ResponseEntity.ok().body(new ResponseObject("success", "Lưu order delivery thành công", orderDeliveryDTO.getCodeDelivery()));
            }
            return ResponseEntity.badRequest().body(new ResponseObject("error", "Lưu order delivery thất bại", null));
    }
    @GetMapping("/bill-code/{billCode}")
    public ResponseEntity<ResponseObject> getByBillCode(@PathVariable String billCode){
        OrderDTO dto = orderService.getOrderByBillCode(billCode);
        if(dto!=null){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lấy đơn hàng thành công by bill code", dto));
        }
        return ResponseEntity.status(400).body(new ResponseObject("error", "Không tìm thấy đơn hàng có bill code như trên", null));
    }
    @GetMapping("/search-order-phone-bill-code/{phone}/{billCode}")
    public ResponseEntity<ResponseObject> getByBillCode(@PathVariable String phone,@PathVariable String billCode ){
        List<OrderDTO> dto = orderService.findOrderByPhoneOrBillCode(phone,billCode);
        if(dto!=null){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lấy đơn hàng thành công by bill code", dto));
        }
        return ResponseEntity.status(400).body(new ResponseObject("error", "Không tìm thấy đơn hàng có bill code như trên", null));
    }
    @GetMapping("/update-delivery/{orderId}/{deliveryId}")
    public ResponseEntity<ResponseObject> updateOrderDelivery(@PathVariable Integer orderId, @PathVariable Integer deliveryId){
        boolean check = orderService.updateDelivery(orderId, deliveryId);
        if(check == true){
            return ResponseEntity.ok().body(new ResponseObject("success", "Cập nhật đơn vị vận chuyển thành công", check));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Cập nhật đơn vị vận chuyển thất bại", null));
    }
    @GetMapping("/export")
    public void exportToExcel(HttpServletResponse response,
                              @RequestParam(value = "employeeId", required = false) Integer employeeId,
                              @RequestParam(value = "creatorId", required = false) Integer creatorId,
                              @RequestParam(value = "businessId", required = false) Integer businessId,
                              @RequestParam(value = "deliveryId", required = false) Integer deliveryId,
                              @RequestParam(value = "orderStatusId", required = false) Integer orderStatusId,
                              @RequestParam(value = "orderTypeId", required = false) Integer orderTypeId,
                              @RequestParam(value = "orderTimeStart", required = false) String orderTimeStart,
                              @RequestParam(value = "orderTimeEnd", required = false) String orderTimeEnd) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=Orders.xlsx");
        LocalDate toDay = LocalDate.now(ZoneId.of("GMT+07:00"));
        String start = null;
        String end = null;
        if (orderTimeStart != null && orderTimeEnd != null) {
            start = orderTimeStart + " 00:00:00";
            end = orderTimeEnd + " 23:59:59";
        }else if(orderTimeStart != null && orderTimeEnd == null){
            start = orderTimeStart + " 00:00:00";
            end = toDay+" 23:59:59";
        }else{
            start = toDay + " 00:00:00";
            end = toDay+" 23:59:59";
        }
        System.out.println(start);
        System.out.println(end);
        List<OrderDTO> listDto = orderService.getAllByCondition(employeeId, creatorId, businessId, deliveryId, orderStatusId, orderTypeId, start, end);
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("orderDto");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("STT");
        headerRow.createCell(1).setCellValue("Mã đơn");
        headerRow.createCell(2).setCellValue("Trạng thái");
        headerRow.createCell(3).setCellValue("Loại đơn");
        headerRow.createCell(4).setCellValue("Tên người nhận");
        headerRow.createCell(5).setCellValue("Địa chỉ");
        headerRow.createCell(6).setCellValue("SĐT");
        headerRow.createCell(7).setCellValue("Tổng tiền");
        headerRow.createCell(8).setCellValue("Thời gian đặt");
        headerRow.createCell(9).setCellValue("Sản phẩm");
        int rowNum = 1;
        for (OrderDTO orderDto : listDto) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(rowNum-1);
            row.createCell(1).setCellValue(orderDto.getBillCode());
            row.createCell(2).setCellValue(orderDto.getOrderStatusDTO().getName());
            row.createCell(3).setCellValue(orderDto.getOrderTypeDTO().getName());
            row.createCell(4).setCellValue(orderDto.getName());
            row.createCell(5).setCellValue(orderDto.getAddress());
            row.createCell(6).setCellValue(orderDto.getPhone());
            row.createCell(7).setCellValue(orderDto.getPaymentAmount());
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String formattedDateTime = dateFormat.format(orderDto.getOrderTime());
            row.createCell(8).setCellValue(formattedDateTime);
            StringBuilder sb = new StringBuilder();
            for (OrderDetailDTO od : orderDto.getOrderDetail()) {
                String nameOd = od.getProductDetailDTO().getCodeName();
                sb.append(nameOd).append(", ");
            }
            String toltalNameOd = sb.substring(0, sb.length() - 2);
            row.createCell(9).setCellValue(toltalNameOd);
        }
        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
