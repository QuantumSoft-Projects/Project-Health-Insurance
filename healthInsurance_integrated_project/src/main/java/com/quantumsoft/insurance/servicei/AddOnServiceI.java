package com.quantumsoft.insurance.servicei;

import com.quantumsoft.insurance.entity.AddOn;

import java.util.List;

public interface AddOnServiceI {

    public String addAddOn(AddOn addOn);

    public List<AddOn> fetchAllAddOns();
}
