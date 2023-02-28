

package com.example.demokoro.serviceImpl;

import com.example.demokoro.dto.TagDTO;

import java.util.List;

public interface ITagService {
    List<TagDTO> getAll();
    TagDTO getById(Integer id);
}
