package com.example.demokoro.service;

import com.example.demokoro.dto.OrderTypeDTO;
import com.example.demokoro.dto.TagDTO;
import com.example.demokoro.models.Tag;
import com.example.demokoro.repository.TagRepository;
import com.example.demokoro.serviceImpl.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService implements ITagService {
    @Autowired
    TagRepository TagRepository;
    @Override
    public List<TagDTO> getAll() {
        List<TagDTO> listDto = new ArrayList<>();
        List<Tag> list = TagRepository.findAll();
        for(Tag var: list){
            listDto.add((new TagDTO(var)));
        }
        return listDto;
    }

    @Override
    public TagDTO getById(Integer id) {
//        TagDTO dto = new OrderTypeDTO();
//        if(orderTypeRepository.findById(id).isPresent()){
//            OrderType orderType = orderTypeRepository.findById(id).get();
//            dto = new OrderTypeDTO(orderType);
//            return dto;
//        }
        return null;
    }



}
