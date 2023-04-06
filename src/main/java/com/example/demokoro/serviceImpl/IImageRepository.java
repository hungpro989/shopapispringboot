package com.example.demokoro.serviceImpl;

import com.example.demokoro.dto.ImageDTO;
import com.example.demokoro.dto.SettingDTO;
import com.example.demokoro.models.Image;
import com.example.demokoro.models.Setting;

import java.util.List;

public interface IImageRepository {
    List<ImageDTO> getAll();
    ImageDTO getById (Integer id);
    boolean deleteById(Integer id);
    boolean save(ImageDTO dto);

}
