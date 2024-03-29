package com.example.demokoro.controller;

import com.example.demokoro.dto.*;
import com.example.demokoro.models.Product;
import com.example.demokoro.service.ImageService;
import com.example.demokoro.service.ProductDetailService;
import com.example.demokoro.service.ProductService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
@RestController
@CrossOrigin()
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductDetailService productDetailService;
    @Autowired
    ImageService imageService;
    @GetMapping
    public ResponseEntity<ResponseObject> getAllProduct(){
        List<ProductDTOAdmin> listDto = productService.getAllProduct();
        if(!listDto.isEmpty()){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lay danh sach san pham thanh cong", listDto));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Lay danh sach san pham that bai", null));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getProductById(@PathVariable Integer id){
        if(productService.checkExistId(id)){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lấy sản phẩm thành công", productService.getProducById(id)));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Không tìm thấy sản phẩm có id như trên", null));
    }
    @PostMapping
    public ResponseEntity<ResponseObject> createProduct(@RequestBody ProductCreateDTO product){
        Product p = new Product(product);
            if(productService.save(p)){
                //save category_product
                productService.createCategoryProduct(product, p);
                //save product detail
                productService.createProductDetail(product,p);
                return ResponseEntity.ok().body(new ResponseObject("success", "Tạo sản phẩm mới thành công", product));
            }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Tạo sản phẩm thất bại2", null));
    }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponseObject> updateProduct(@RequestBody ProductCreateDTO product, @PathVariable Integer id){
        String check = productService.updateAndCheckProduct(product,id);
        if(Objects.equals(check, "true")){
            return ResponseEntity.ok().body(new ResponseObject("success", "Cập nhật sản phẩm mới thành công", null));
        }else if(Objects.equals(check, "false01")){
            return ResponseEntity.badRequest().body(new ResponseObject("error", "Đã tồn tại tên sản phẩm: "+product.getName(), null));
        }else if(Objects.equals(check, "false02")){
            return ResponseEntity.badRequest().body(new ResponseObject("error", "Id sản phẩm không đúng hoặc không khớp, ID trên url= "+id+" hoặc Id trong mục sản phẩm= "+product.getId(),null));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Lỗi nội bộ", null));
    }
    // tạo category product

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable("id") Integer id) {
        productService.deleteById(id);
    }

    //copy product new

    @PostMapping("/copy-product/{id}")
    public ResponseEntity<ResponseObject> copyProduct(@PathVariable Integer id){
        String check = productService.copyProduct(id);
        if(check=="true"){
            return ResponseEntity.ok().body(new ResponseObject("success", "Copy sản phẩm mới thành công", null));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Copy sản phẩm thất bại", null));
    }

    //copy product detail new
    @PostMapping("/copy-product-detail/{id}")
    public ResponseEntity<ResponseObject> copyProductDetail(@PathVariable Integer id){
        String check = productDetailService.copyProductDetail(id);
        if(check=="true"){
            return ResponseEntity.ok().body(new ResponseObject("success", "Copy sản phẩm con mới thành công", null));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Copy sản phẩm con thất bại", null));
    }

    //get list product detail
    @GetMapping("/product-detail")
    public ResponseEntity<ResponseObject> getAllProductDetail(){
        List<ProductDetailDTO> listDto = productDetailService.getAllProductDetail();
        if(!listDto.isEmpty()){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lay danh sach san pham con thanh cong", listDto));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Lay danh sach san pham con that bai", null));
    }
    @GetMapping("/export")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=ProductDetails.xlsx");

        List<ProductDetailDTO> listDto = productDetailService.getAllProductDetail();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("product");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("STT");
        headerRow.createCell(1).setCellValue("Tên mã");
        headerRow.createCell(2).setCellValue("Giá");
        headerRow.createCell(3).setCellValue("Giá giảm");
        headerRow.createCell(4).setCellValue("Giá nhập");
        headerRow.createCell(5).setCellValue("Tổng sp");
        headerRow.createCell(6).setCellValue("Tồn kho");
        headerRow.createCell(7).setCellValue("Chờ Vc");
        int rowNum = 1;
        for (ProductDetailDTO productDTO : listDto) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(rowNum-1);
            row.createCell(1).setCellValue(productDTO.getCodeName()!=null?productDTO.getCodeName():"");
            row.createCell(2).setCellValue(productDTO.getPrice() !=null ? productDTO.getPrice():0);
            row.createCell(3).setCellValue(productDTO.getDiscount()!=null ? productDTO.getDiscount():0);
            row.createCell(4).setCellValue(productDTO.getCost()!=null ? productDTO.getCost():0);
            row.createCell(5).setCellValue(productDTO.getTotalQuantity()!=null ? productDTO.getTotalQuantity():0);
            row.createCell(6).setCellValue(productDTO.getValidQuantity()!=null ? productDTO.getValidQuantity():0);
            row.createCell(7).setCellValue(productDTO.getHoldQuantity()!=null ? productDTO.getHoldQuantity():0);
        }
        workbook.write(response.getOutputStream());
        workbook.close();
    }


}
