package com.quantumsoft.insurance.servicei;

import com.quantumsoft.insurance.entity.Rider;

import java.util.List;

public interface RiderServiceI {

    public String addRider(Rider rider);

    public List<Rider> fetchAllRiders();
}
