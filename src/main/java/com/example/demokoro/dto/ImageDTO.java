package com.example.demokoro.dto;

import com.example.demokoro.models.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTO {
    private Integer id;
    private String urlImage;
    private String fileName;
    public ImageDTO(Image image) {
        this.id = image.getId();
        this.urlImage = image.getUrlImage();
        this.fileName=image.getFileName();
    }
}
