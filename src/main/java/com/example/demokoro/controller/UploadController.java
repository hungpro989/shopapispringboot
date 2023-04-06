package com.example.demokoro.controller;

import com.example.demokoro.dto.ImageDTO;
import com.example.demokoro.dto.ResponseObject;
import com.example.demokoro.service.ImageService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/file")
@CrossOrigin
public class UploadController {
    @Autowired
    ImageService imageService;
//    //upload từng ảnh 1
//    @PostMapping("/upload-images")
//    public ResponseEntity<ResponseObject> uploadFiles(@RequestParam("file") MultipartFile multipartFiles)throws IOException {
//        System.out.println(multipartFiles);
//        if (multipartFiles.isEmpty()) {
//            return ResponseEntity.badRequest().body(new ResponseObject("error", "Upload ảnh thất bại", null));
//        }
//
//        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFiles.getOriginalFilename()));
//        Path uploadPath = Paths.get("Upload/Images");
//        if (!Files.exists(uploadPath)) {
//            Files.createDirectories(uploadPath);
//        }
//        String fileCode = RandomStringUtils.randomAlphanumeric(8);
//        Path filePath = uploadPath.resolve(fileCode + "-" + fileName);
//        Files.copy(multipartFiles.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//
//        double size = ((double)multipartFiles.getSize())/(1024*1024);
//        String formatSize = new DecimalFormat("#.##").format(size);
//        if(Double.parseDouble(formatSize)>5){
//            return ResponseEntity.badRequest().body(new ResponseObject("error", "Upload ảnh thất bại, kích thước ảnh lớn hơn 5mb", null));
//        }
//        ImageDTO imageDTO = new ImageDTO();
//        imageDTO.setFileName(fileName);
//        imageDTO.setUrlImage("/images/" + fileCode);
//        imageService.save(imageDTO);
//
//        return ResponseEntity.ok().body(new ResponseObject("success", "Upload ảnh thành công", imageDTO));
//    }
@PostMapping("/upload-images")
public ResponseEntity<ResponseObject> uploadFiles(@RequestParam("file") MultipartFile[] multipartFiles) throws IOException {
    System.out.println(multipartFiles);
    List<ImageDTO> uploadedImages = new ArrayList<>();
    for (MultipartFile multipartFile : multipartFiles) {
        if (multipartFile.isEmpty()) {
            continue;
        }

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        Path uploadPath = Paths.get("Upload/Images");
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        String fileCode = RandomStringUtils.randomAlphanumeric(8);
        Path filePath = uploadPath.resolve(fileCode + "-" + fileName);
        Files.copy(multipartFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        double size = ((double) multipartFile.getSize()) / (1024 * 1024);
        String formatSize = new DecimalFormat("#.##").format(size);
        if (Double.parseDouble(formatSize) > 5) {
            return ResponseEntity.badRequest().body(new ResponseObject("error", "Upload ảnh thất bại, kích thước ảnh lớn hơn 5mb", null));
        }
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setFileName(fileName);
        imageDTO.setUrlImage("/file/images/" + fileCode);
        imageService.save(imageDTO);
        uploadedImages.add(imageDTO);
    }

    return ResponseEntity.ok().body(new ResponseObject("success", "Upload ảnh thành công", uploadedImages));
}

    // hiển thị list thông tin ảnh ở DB
    @GetMapping("/list-images")
    public ResponseEntity<ResponseObject> uploadFile(){
        List<ImageDTO> dto = imageService.getAll();

        return ResponseEntity.ok().body(new ResponseObject("success", "Lấy ảnh thành công", dto));
    }
    //hiển thị ảnh với url/images/{tên_ảnh}
    @GetMapping("/images/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) throws IOException {
        Resource resource = new UrlResource("file:./Upload/Images/" + imageName);
        if (resource.exists()) {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
