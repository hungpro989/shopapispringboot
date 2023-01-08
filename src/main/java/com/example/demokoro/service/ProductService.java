package com.example.demokoro.service;

import com.example.demokoro.dto.ProductCreateDTO;
import com.example.demokoro.dto.ProductDTOAdmin;
import com.example.demokoro.models.Category;
import com.example.demokoro.models.CategoryProduct;
import com.example.demokoro.models.Product;
import com.example.demokoro.models.ProductDetail;
import com.example.demokoro.repository.CategoryRepository;
import com.example.demokoro.repository.ProductRepository;
import com.example.demokoro.serviceImpl.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import static com.example.demokoro.common.common.toSlug;

@Service
@Transactional
public class ProductService implements IProductService {
    @Autowired
    CategoryProductService categoryProductService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductDetailService productDetailService;
    @Override
    public List<ProductDTOAdmin> getAllProduct() {
        List<ProductDTOAdmin> listDto = new ArrayList<>();
        List<Product> list = productRepository.findAll();
        for (Product product : list) {
            listDto.add(new ProductDTOAdmin(product));
        }
        return listDto;
    }

    @Override
    public ProductDTOAdmin getProducById(Integer id) {
        ProductDTOAdmin dto = new ProductDTOAdmin();
        if(productRepository.findById(id).isPresent()){
            Product p = productRepository.findById(id).get();
            dto = new ProductDTOAdmin(p);
            return dto;
        }
        return dto;
    }

    @Override
    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public boolean save(Product product) {
        try{
            product.setSlug(toSlug(product.getName()));
            productRepository.save(product);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public Product checkExistName(String name) {
        return productRepository.findProductByName(name);
    }

    @Override
    public boolean checkExistId(Integer id) {
        Product p = productRepository.findById(id).orElse(null);
        if(p!=null){
            //tồn tại trong db
            return true;
        }
        //ko tồn tại trong db
        return false;
    }

    @Override
    public String updateAndCheckProduct(ProductCreateDTO productCreateDTO, Integer id) {
        ProductDTOAdmin proDto = getProducById(productCreateDTO.getId());
        if(id == productCreateDTO.getId() && proDto!=null){
            Product p = new Product(productCreateDTO);
            //check name đã có trong DB hay chưa
            if(checkExistName(productCreateDTO.getName())==null){
                return createProduct(productCreateDTO, id, p);
            }else if(productCreateDTO.getName().equalsIgnoreCase(proDto.getName())){
                return createProduct(productCreateDTO, id, p);
            }else{
                return "false01";
            }
        }
        return "false02";
    }

    @Override
    public String copyProduct(Integer id) {
        ProductDTOAdmin productDTOAdmin = getProducById(id);
        ProductCreateDTO productCreateDTO = new ProductCreateDTO(productDTOAdmin);
        Product p = new Product(productCreateDTO);
        p.setName(p.getName()+" copy");
        if(save(p)){
            productDTOAdmin.getProductDetail().forEach(var -> {
                        ProductDetail productDetail = new ProductDetail(var);
                        productDetail.setId(null);
                        productDetail.setCodeName(var.getCodeName()+" copy");
                        productDetail.setProducts(p);
                        productDetailService.save(productDetail);
                    }
            );
            productDTOAdmin.getCategoryProduct().forEach(var->{
                Category category =  categoryRepository.findById(var.getId()).orElse(null);
                if (category!=null){
                    CategoryProduct categoryProduct = new CategoryProduct();
                    categoryProduct.setProduct(p);
                    categoryProduct.setCategory(category);
                    categoryProductService.save(categoryProduct);
                }
            });
            return "true";
        }
        return "false";
    }

    private String createProduct(ProductCreateDTO productCreateDTO, Integer id, Product p) {
        if(!categoryProductService.findCategoryProductByProductId(id).isEmpty()){
            categoryProductService.deleteById(p.getId());
        }
        if(save(p)){
            createProductDetail(productCreateDTO,p);
            createCategoryProduct(productCreateDTO, p);
        }

        return "true";
    }


    public void createCategoryProduct(@RequestBody ProductCreateDTO productCreateDTO, Product p) {
        if(productCreateDTO.getCategoryProduct()!= null){
            productCreateDTO.getCategoryProduct().forEach(var->{
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
    }
    //tạo product detail
    public void createProductDetail(@RequestBody ProductCreateDTO productCreateDTO, Product p) {
        if(productCreateDTO.getProductDetail()!=null){
            productCreateDTO.getProductDetail().forEach(var -> {
                        ProductDetail productDetail = new ProductDetail(var);
                        productDetail.setProducts(p);
                        productDetailService.save(productDetail);
                    }
            );
        }
    }
}
