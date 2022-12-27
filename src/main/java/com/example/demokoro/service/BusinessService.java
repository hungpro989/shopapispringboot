package com.example.demokoro.service;

import com.example.demokoro.dto.BusinessDTO;
import com.example.demokoro.models.Business;
import com.example.demokoro.repository.BusinessRepository;
import com.example.demokoro.serviceImpl.IBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class BusinessService implements IBusinessService {
    @Autowired
    BusinessRepository businessRepository;
    @Override
    public List<BusinessDTO> getAllBusiness() {
        List<BusinessDTO> listDto = new ArrayList<>();
        List<Business> list = businessRepository.findAll();
        for(Business var: list){
            listDto.add(new BusinessDTO(var));
        }
        return listDto;
    }

    @Override
    public BusinessDTO getBusinessById(Integer id) {
        BusinessDTO dto = new BusinessDTO();
        if(businessRepository.findById(id).isPresent()){
            Business business = businessRepository.findById(id).get();
            dto = new BusinessDTO(business);
            return dto;
        }
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        try {
            businessRepository.deleteById(id);
            return true;
        }catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean save(Business business) {
        try{
            businessRepository.save(business);
            return true;
        }catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean checkExistName(String name) {
        if(businessRepository.findBusinessByCodeName(name)!=null){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkExistId(Integer id) {
        Business b = businessRepository.findById(id).orElse(null);
        if (businessRepository.findById(id).orElse(null)!=null) {
            //tồn tại trong db
            return true;
        }
        //ko tồn tại trong db
        return false;
    }
}
