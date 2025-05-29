package com.quantumsoft.insurance.controller;

import com.quantumsoft.insurance.entity.AddOn;
import com.quantumsoft.insurance.entity.CustomizedPolicy;
import com.quantumsoft.insurance.entity.Rider;
import com.quantumsoft.insurance.servicei.CustomizedPolicyServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/customizedPolicy")
@CrossOrigin("*")
public class CustomizedPolicyController {

    @Autowired
    private CustomizedPolicyServiceI service;

    // @RequestBody CustomizedPolicy customizedPolicy inside this we are passing only premium plan

    @PostMapping(value = "/customize/{policyId}/{userId}")
    public ResponseEntity<String> customizePolicy(@PathVariable Long policyId, @PathVariable Long userId,
                                                  @RequestBody CustomizedPolicy customizedPolicy){
        String message = service.customizePolicy(policyId, userId, customizedPolicy);
        return new ResponseEntity<String>(message, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/addRider/{customizedPolicyId}/{riderId}")
    public ResponseEntity<Rider> addRiderToPolicy(@PathVariable Long customizedPolicyId, @PathVariable Long riderId){
       Rider rider = service.addRiderToPolicy(customizedPolicyId,riderId);
        return new ResponseEntity<Rider>(rider, HttpStatus.OK);
    }
//
    @PatchMapping(value = "/addAddOn/{customizedPolicyId}/{addOnId}")
    public ResponseEntity<AddOn> addAddOnToPolicy(@PathVariable Long customizedPolicyId, @PathVariable Long addOnId){
        AddOn addOn = service.addAdOnToPolicy(customizedPolicyId,addOnId);
        return new ResponseEntity<AddOn>(addOn, HttpStatus.OK);
    }
}
