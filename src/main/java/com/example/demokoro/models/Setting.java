package com.example.demokoro.models;

import com.example.demokoro.dto.SettingDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "setting")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Setting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "my_key")
    private String myKey;
    @Column(name = "value")
    private String value;
    @Column(name = "data")
    private String data;

    public Setting(SettingDTO dto) {
        this.id=dto.getId();
        this.myKey = dto.getMyKey();
        this.value = dto.getValue();
        this.data = dto.getData();
    }
}
