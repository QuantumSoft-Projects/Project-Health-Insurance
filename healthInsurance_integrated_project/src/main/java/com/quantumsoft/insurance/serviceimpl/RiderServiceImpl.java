package com.quantumsoft.insurance.serviceimpl;

import com.quantumsoft.insurance.entity.Rider;
import com.quantumsoft.insurance.repository.RiderRepository;
import com.quantumsoft.insurance.servicei.RiderServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiderServiceImpl implements RiderServiceI {

    @Autowired
    private RiderRepository repository;

    @Override
    public String addRider(Rider rider) {
        repository.save(rider);
        return "Rider saved successfully...!";
    }

    @Override
    public List<Rider> fetchAllRiders() {
        return repository.findAll();
    }
}
