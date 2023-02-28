package com.example.demokoro.dto;

import com.example.demokoro.models.OrderTag;
import com.example.demokoro.models.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagDTO {
    private Integer id;
    private String name;
    public TagDTO(Tag var) {
        this.id = var.getId();
        this.name = var.getName();
    }
    public TagDTO(OrderTag o){
        this.id=o.getTag().getId();
        this.name=o.getTag().getName();
    }
}
