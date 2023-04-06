package com.example.demokoro.models;

import com.example.demokoro.dto.ImageDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "image")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "url_image")
    private String urlImage;

    @Column(name="file_name")
    private String fileName;
    public Image(ImageDTO dto) {
        this.id=dto.getId();
        this.urlImage=dto.getUrlImage();
        this.fileName=dto.getFileName();
    }
}
