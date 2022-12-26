package com.example.demokoro.controller;

import com.example.demokoro.dto.*;
import com.example.demokoro.models.Category;
import com.example.demokoro.models.CategoryProduct;
import com.example.demokoro.models.Product;
import com.example.demokoro.models.ProductDetail;
import com.example.demokoro.repository.CategoryRepository;
import com.example.demokoro.repository.ProductRepository;
import com.example.demokoro.service.CategoryProductService;
import com.example.demokoro.service.CategoryService;
import com.example.demokoro.service.ProductDetailService;
import com.example.demokoro.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RequestMapping("/api/v1/products")
@RestController
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductDetailService productDetailService;
    @Autowired
    private CategoryProductService categoryProductService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

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
        try{
            if(productService.save(p)!=null){
                //save category_product
                createCategoryProduct(product, p);
                //save product detail
                createProductDetail(product,p);
                return ResponseEntity.ok().body(new ResponseObject("success", "Tạo sản phẩm mới thành công", product));
            }
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseObject("error", "Tạo sản phẩm thất bại1", null));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Tạo sản phẩm thất bại2", null));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateProduct(@RequestBody ProductCreateDTO product, @PathVariable Integer id){
        ProductDTOAdmin proDto = productService.getProducById(id);
        if(id == product.getId() && proDto!=null){
            Product p = new Product(product);
            if(productService.checkExistName(p.getName())){
                categoryProductService.deleteById(p.getId());
                productService.save(p);
                categoryProductService.deleteById(p.getId());
                createCategoryProduct(product, p);
                createProductDetail(product,p);
                return ResponseEntity.ok().body(new ResponseObject("success", "Update sản phẩm mới thành công1", product));
            }else if(proDto.getName().equalsIgnoreCase(p.getName())){
                categoryProductService.deleteById(p.getId());
                productService.save(p);
                createCategoryProduct(product, p);
                createProductDetail(product,p);
                return ResponseEntity.ok().body(new ResponseObject("success", "Update sản phẩm mới thành công2", product));
            }else{
                return ResponseEntity.badRequest().body(new ResponseObject("error", "Tên sp đã tồn tại", null));
            }
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Id sản phẩm ko tồn tại", null));
    }
    // tạo category product
    private void createCategoryProduct(@RequestBody ProductCreateDTO product, Product p) {
        product.getCategoryProduct().forEach(var->{
            Category category = new Category();
            category = categoryRepository.findById(var.getCategoryId()).orElse(null);
            if (category!=null){
                CategoryProduct categoryProduct = new CategoryProduct();
                categoryProduct.setProduct(p);
                categoryProduct.setCategory(category);
                categoryProductService.save(categoryProduct);
            }
        });
    }
    //tạo product detail
    private void createProductDetail(@RequestBody ProductCreateDTO product, Product p) {
        product.getProductDetail().forEach(var -> {
                ProductDetail productDetail = new ProductDetail(var);
                productDetail.setProducts(p);
                productDetailService.save(productDetail);
            }
        );
    }
}
