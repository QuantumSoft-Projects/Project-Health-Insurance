package com.quantumsoft.insurance.serviceimpl;

import com.quantumsoft.insurance.entity.AddOn;
import com.quantumsoft.insurance.repository.AddOnRepository;
import com.quantumsoft.insurance.servicei.AddOnServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddOnServiceImpl implements AddOnServiceI {

    @Autowired
    private AddOnRepository repository;

    @Override
    public String addAddOn(AddOn addOn) {
        repository.save(addOn);
        return "Addon saved successfully...!";
    }

    @Override
    public List<AddOn> fetchAllAddOns() {
        return repository.findAll();
    }
}
