package com.quantumsoft.insurance.serviceimpl;

import com.quantumsoft.insurance.entity.Policy;
import com.quantumsoft.insurance.enums.PremiumPlan;
import com.quantumsoft.insurance.exception.PolicyNotFoundException;
import com.quantumsoft.insurance.repository.PolicyRepository;
import com.quantumsoft.insurance.servicei.PolicyServiceI;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PolicyServiceImpl implements PolicyServiceI {

    @Autowired
    private PolicyRepository policyRepository;

    @Override
    public Policy addPolicy(Policy policy) {
        return policyRepository.save(policy);
    }

//    @Override
//    public Policy customizePolicy(Long policyId, PremiumPlan premiumPlan) {
//        Optional<Policy> optional = policyRepository.findById(policyId);
//        if(optional.isPresent()){
//            Policy policy = optional.get();
//            policy.setPremiumPlan(premiumPlan);
//            return policyRepository.save(policy);
//        }
//        return null;
//    }

    @Override
    public Policy getPolicyById(Long policyId) {
        Optional<Policy> policyOptional = policyRepository.findById(policyId);
        if (policyOptional.isPresent()) {
            return policyOptional.get();
        } else {
            throw new IllegalArgumentException("Policy with id " + policyId + " not found");
        }
    }

    @Override
    public java.util.List<Policy> getAllPolicies() {
        return java.util.List.of();
    }

    @Override
    public Policy updatePolicy(Long policyId, @Valid Policy policyDetails) {
        Optional<Policy> policyOptional = policyRepository.findById(policyId);
        if(policyOptional.isPresent()) {
            return policyRepository.save(policyDetails);
        }
        throw new PolicyNotFoundException("Policy record with given id not found in database");
    }

    @Override
    public void deletePolicy(Long policyId) {
        Policy policy = getPolicyById(policyId); // Checking if policy exists
        policyRepository.delete(policy); // Deleting policy
    }

    @Override
    public Optional<Policy> getbyId() {
        return Optional.empty();
    }

}
