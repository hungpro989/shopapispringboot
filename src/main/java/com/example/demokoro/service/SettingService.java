package com.example.demokoro.service;

import com.example.demokoro.dto.SettingDTO;
import com.example.demokoro.models.Setting;
import com.example.demokoro.repository.SettingRepository;
import com.example.demokoro.serviceImpl.ISettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class SettingService implements ISettingRepository {
    @Autowired
    SettingRepository settingRepository;
    @Override
    public boolean save(Setting setting) {
        settingRepository.save(setting);
        return true;
    }

    @Override
    public List<SettingDTO> getAllSetting() {
        List<SettingDTO> listDTO = new ArrayList<>();
        List<Setting> list = settingRepository.findAll();
        for(Setting var: list){
            listDTO.add(new SettingDTO(var));
        }
        return listDTO;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }

    @Override
    public SettingDTO findSettingById(Integer id) {
        return null;
    }

    @Override
    public SettingDTO findSettingByKey(String s) {
        Setting setting = settingRepository.findSettingByMyKey(s);
        return new SettingDTO(setting);
    }

    @Override
    public boolean updateSetting(SettingDTO settingDTOInput, String myKey) {
        Setting setting = new Setting(settingDTOInput);
        setting.setId(findSettingByKey(myKey).getId());
        settingRepository.save(setting);
        return true;
    }
}
