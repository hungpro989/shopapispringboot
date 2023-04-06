package com.example.demokoro.service;

import com.example.demokoro.dto.ImageDTO;
import com.example.demokoro.models.Image;
import com.example.demokoro.repository.ImageRepository;
import com.example.demokoro.serviceImpl.IImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
@Service
public class ImageService implements IImageRepository {
    @Autowired
    ImageRepository imageRepository;
    @Override
    public List<ImageDTO> getAll() {
        List<ImageDTO> listDto = new ArrayList<>();
        List<Image> listImage = imageRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        for(Image i:listImage){
            listDto.add(new ImageDTO(i));
        }
        return listDto;
    }

    @Override
    public ImageDTO getById(Integer id) {
        Image image =imageRepository.findById(id).orElse(null);
        return new ImageDTO(image);
    }

    @Override
    public boolean deleteById(Integer id) {
        try{
            imageRepository.deleteById(id);
            return false;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean save(ImageDTO dto) {
        try{
            Image image = new Image(dto);
            imageRepository.save(image);
            return true;
        }catch (Exception e) {
            return false;

        }
    }
}
