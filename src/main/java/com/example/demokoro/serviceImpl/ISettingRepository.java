package com.example.demokoro.serviceImpl;

import com.example.demokoro.dto.SettingDTO;
import com.example.demokoro.models.Setting;

import java.util.List;

public interface ISettingRepository {
    boolean save(Setting setting);
    List<SettingDTO> getAllSetting();
    boolean deleteById(Integer id);
    SettingDTO findSettingById(Integer id);
    SettingDTO findSettingByKey(String s);
    boolean updateSetting(SettingDTO settingDTO, String key);

}
