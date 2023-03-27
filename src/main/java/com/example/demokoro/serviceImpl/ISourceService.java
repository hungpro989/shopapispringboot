

package com.example.demokoro.serviceImpl;

import com.example.demokoro.dto.SourceDTO;

import java.util.List;

public interface ISourceService {
    List<SourceDTO> getAll();
    SourceDTO getById(Integer id);
}
