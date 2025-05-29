package com.quantumsoft.insurance.controller;

import com.quantumsoft.insurance.entity.Policy;
import com.quantumsoft.insurance.enums.PremiumPlan;
import com.quantumsoft.insurance.servicei.PolicyServiceI;
import com.quantumsoft.insurance.serviceimpl.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/policy")
@CrossOrigin("*")
public class PolicyController {

    @Autowired
    private PolicyServiceI policyService;

    @Autowired
    private JwtService jwtService;

    @PostMapping(value = "/add")
    public ResponseEntity<Policy> addPolicy(@Valid @RequestBody Policy policy, @RequestHeader("Authorization")String bearerToken){
        String token = bearerToken.substring(7);
        String role = jwtService.extractRole(token);
        if(role.equals("SUPERADMIN")) {
            System.out.println("Role: SuperAdmin");
            Policy savedPolicy = policyService.addPolicy(policy);
            return new ResponseEntity<Policy>(savedPolicy, HttpStatus.CREATED);
        }else
            return new ResponseEntity<Policy>(HttpStatus.UNAUTHORIZED);
    }

//    @PatchMapping(value = "/customize/{policyId}")
//    public ResponseEntity<Policy> customizePolicy(@PathVariable Long policyId, @RequestParam PremiumPlan premiumPlan){
//        Policy updatedPolicy = policyService.customizePolicy(policyId, premiumPlan);
//        return new ResponseEntity<Policy>(updatedPolicy, HttpStatus.OK);
//    }

    @GetMapping(value = "/all")
    public List<Policy> getAllPolicies() {
        return policyService.getAllPolicies();
    }

    @GetMapping("/{policyId}")
    public ResponseEntity<Policy> getPolicyById(@PathVariable Long policyId, @RequestHeader("Authorization")String bearerToken) {
        String token = bearerToken.substring(7);
        String role = jwtService.extractRole(token);
        if(role.equals("SUPERADMIN")) {
            Policy policy = policyService.getPolicyById(policyId);
            return new ResponseEntity<Policy>(policy, HttpStatus.OK);
        }else
            return new ResponseEntity<Policy>(HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/{policyId}")
    public ResponseEntity<Policy> updatePolicy(@PathVariable Long policyId,  @RequestBody Policy policy, @RequestHeader("Authorization")String bearerToken) {
        String token = bearerToken.substring(7);
        String role = jwtService.extractRole(token);
        if(role.equals("SUPERADMIN")) {
            Policy updatedPolicy = policyService.updatePolicy(policyId, policy);
            return new ResponseEntity<Policy>(updatedPolicy, HttpStatus.OK);
        }else
            return new ResponseEntity<Policy>(HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("/{policyId}")
    public ResponseEntity<Void> deletePolicy(@PathVariable Long policyId, @RequestHeader("Authorization")String bearerToken) {
        String token = bearerToken.substring(7);
        String role = jwtService.extractRole(token);
        if(role.equals("SUPERADMIN")) {
                try {
                    policyService.deletePolicy(policyId);
                    return ResponseEntity.noContent().build();
                } catch (RuntimeException e) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }
        }else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
