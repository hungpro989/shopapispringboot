package com.example.demokoro.controller;

import com.example.demokoro.dto.*;
import com.example.demokoro.models.Employee;
import com.example.demokoro.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @GetMapping
    public ResponseEntity<ResponseObject> getAll(){
        List<EmployeeDTO> listDto = employeeService.getAll();
        if(!listDto.isEmpty()){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lấy danh sách nhân viên thành công", listDto));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Lấy danh sách nhân viên thất bại", null));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getById(@PathVariable Integer id){
        if(employeeService.checkExistId(id)){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lấy nhân viên thành công", employeeService.getById(id)));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Không tìm thấy trang nhân viên có id như trên", null));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> delete(@PathVariable("id") Integer id){
        if(employeeService.checkExistId(id)){
            employeeService.deleteById(id);
            return ResponseEntity.ok().body(new ResponseObject("success", "Xoá nhân viên thành công", null));
        };
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Id nhân viên không tồn tại", null));
    }
    @PostMapping
    public ResponseEntity<ResponseObject> create(@RequestBody EmployeeDTO dto){
        Employee b = new Employee(dto);
        if(!employeeService.checkExistEmail(dto.getEmail()) && !employeeService.checkExistPhone(dto.getPhone())){
            try{
                employeeService.save(b);
                return ResponseEntity.ok().body(new ResponseObject("success", "Thêm nhân viên mới thành công", dto));
            }catch (Exception e) {
                return ResponseEntity.badRequest().body(new ResponseObject("error", "Thêm nhân viên mới thất bại1", null));
            }
        }else{
            return ResponseEntity.badRequest().body(new ResponseObject("error", "Email hoặc sdt đã được đăng ký", null));
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> update(@RequestBody Employee employee, @PathVariable Integer id){
        EmployeeDTO dto = employeeService.getById(id);
        if(dto != null){
            //kiểm tra allEmployee nhưng phone, email là ko trùng ==> cập nhật luôn
            if(!employeeService.checkExistPhone(employee.getPhone()) && !employeeService.checkExistEmail(employee.getEmail())) {
                if (employeeService.save(employee)) {
                    return ResponseEntity.ok().body(new ResponseObject("success", "Cập nhật thông tin thành công", employee));
                }
                return ResponseEntity.badRequest().body(new ResponseObject("error", "Cập nhật thông tin thất bại", null));
                //kiểm tra empPhone, empEmail trùng với dtoPhone, dtoEmail các trường khác cập nhật => cập nhật luôn
            }else if(employee.getPhone().equalsIgnoreCase(dto.getPhone()) && employee.getEmail().equalsIgnoreCase(dto.getEmail())) {
                if (employeeService.save(employee)) {
                    return ResponseEntity.ok().body(new ResponseObject("success", "Cập nhật thông tin thành công", employee));
                }
                return ResponseEntity.badRequest().body(new ResponseObject("error", "Cập nhật thông tin thất bại", null));
            //Lỗi sdt, email trùng ==>error
            }else {
                return ResponseEntity.badRequest().body(new ResponseObject("error", "Sdt hoặc email đã tồn tại", null));
            }
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Không tìm thấy nhân viên nào với ID này", null));
    }

}
