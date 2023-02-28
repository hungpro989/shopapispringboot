package com.example.demokoro.service;

import com.example.demokoro.dto.AddressDTO;
import com.example.demokoro.models.Address;
import com.example.demokoro.repository.AddressRepository;
import com.example.demokoro.serviceImpl.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService implements IAddressService {

    @Autowired
    AddressRepository addressRepository;
    @Override
    public List<AddressDTO> getAllAddress() {
        List<AddressDTO>  addressDTO= new ArrayList<>();
        List<Address> address = addressRepository.findAll(Sort.by(Sort.Direction.ASC, "province"));
        for(Address var: address){
            addressDTO.add(new AddressDTO(var));
        }
        return addressDTO;
    }

    @Override
    public List<String> getAllProvince() {
        List<String> address = addressRepository.getAllByProvince();
        return address;
    }

    @Override
    public List<String> getAllDistrict(String province) {
        List<String> address = addressRepository.getAllByDistrict(province);
        return address;
    }

    @Override
    public List<String> getAllWard(String district) {
        List<String> address = addressRepository.getAllByWard(district);
        return address;
    }

    @Override
    public boolean checkExistName(String name) {
        return false;
    }

    @Override
    public boolean checkExistId(Integer id) {
        return false;
    }

    @Override
    public boolean save(Address address) {
        return false;
    }

    @Override
    public Address getAddressById(Integer id) {
        return null;
    }
}
