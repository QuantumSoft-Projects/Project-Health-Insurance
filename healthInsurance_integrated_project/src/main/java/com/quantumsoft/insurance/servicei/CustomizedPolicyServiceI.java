package com.quantumsoft.insurance.servicei;

import com.quantumsoft.insurance.entity.AddOn;
import com.quantumsoft.insurance.entity.CustomizedPolicy;
import com.quantumsoft.insurance.entity.Rider;

public interface CustomizedPolicyServiceI {

    public String customizePolicy(Long policyId, Long userId, CustomizedPolicy customizedPolicy);

    public Rider addRiderToPolicy(Long customizedPolicyId, Long riderId);

    public AddOn addAdOnToPolicy(Long customizedPolicyId, Long addOnId);
}
