package com.quantumsoft.insurance.servicei;

import com.quantumsoft.insurance.entity.Policy;
import com.quantumsoft.insurance.enums.PremiumPlan;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

public interface PolicyServiceI {
   public Policy addPolicy(Policy policy);

   //public Policy customizePolicy(Long policyId, PremiumPlan premiumPlan);

   Policy getPolicyById(Long policyId);

   List<Policy> getAllPolicies();

   Policy updatePolicy(Long policyId, Policy policy);

   void deletePolicy(Long policyId);

   Optional<Policy> getbyId();
}
