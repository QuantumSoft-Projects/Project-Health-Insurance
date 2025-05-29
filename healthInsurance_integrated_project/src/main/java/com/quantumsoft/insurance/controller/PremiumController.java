package com.quantumsoft.insurance.controller;

import com.quantumsoft.insurance.entity.Premium;
import com.quantumsoft.insurance.servicei.PremiumServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/api/premium")
@CrossOrigin("*")
public class PremiumController {

    @Autowired
    private PremiumServiceI service;


    // API to calculate Premium: Here we have to pass total premium amount, Premium plan(monthly, quarterly, half-quarterly, annually)
    // we have to pass period according to policy plan you have selected.
    // If total premium is 240000 and you have selected to pay premium monthly and according to policy plan duration
    // you have to pa premium

    @GetMapping(value = "/calculator")
    public ResponseEntity<String> premiumCalculator(@RequestParam Double totalPremium, @RequestParam String premiumPlan, @RequestParam Integer duration){
        String premium = service.calculatePremium(totalPremium, premiumPlan, duration);
       return new ResponseEntity<String>(premium, HttpStatus.OK);
    }
//
//    @PostMapping(value = "/premiumDetails/{policyId}/{userId}")
//    public ResponseEntity<String> premiumDetails(@PathVariable Long policyId, @PathVariable Long userId){
//        String message = service.premiumDetails(policyId, userId);
//        return new ResponseEntity<String>(message, HttpStatus.OK);
//    }
//
    @PatchMapping(value = "/pay/{premiumId}")
    public ResponseEntity<String> payPremium(@PathVariable Long premiumId){
        String message = service.payPremium(premiumId);
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }

    @PatchMapping(value = "/generateReceipt/{premiumId}")
    public ResponseEntity<String> generateReceipt(@PathVariable Long premiumId) throws FileNotFoundException {
        String message = service.generateReceipt(premiumId);
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }

    @GetMapping(value = "/downloadReceipt/{premiumId}", produces="application/pdf")
    public ResponseEntity<Resource> downloadReceipt(@PathVariable Long premiumId) throws FileNotFoundException {
        Resource resource = service.downloadReceipt(premiumId);
        return new ResponseEntity<Resource>(resource, HttpStatus.OK);
    }

    @GetMapping(value = "/allByUserId/{userId}")
    public ResponseEntity<List<Premium>> getPremiumsByUserId(@PathVariable Long userId) {
        System.out.println("in premium controller -> inside getPremiumsByUserId");
        List<Premium> premiums = service.getPremiumsByUserId(userId);
        return new ResponseEntity<List<Premium>>(premiums, HttpStatus.OK);
    }

    @GetMapping(value = "/one/{premiumId}")
    public ResponseEntity<Premium> getPremium(@PathVariable Long premiumId) {
        Premium premium = service.getPremium(premiumId);
        return new ResponseEntity<Premium>(premium, HttpStatus.OK);
    }

    @GetMapping(value = "/allByPolicyId/{customizedPolicyId}")
    public ResponseEntity<List<Premium>> getPremiumsByPolicyId(@PathVariable Long customizedPolicyId) {
        List<Premium> premiums = service.getPremiumsByPolicyId(customizedPolicyId);
        return new ResponseEntity<List<Premium>>(premiums, HttpStatus.OK);
    }

    @GetMapping(value = "/renew/{customizedPolicyId}")
    public ResponseEntity<String> renewPolicy(@PathVariable Long customizedPolicyId){
        String message = service.renewPolicy(customizedPolicyId);
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }

    @PatchMapping(value = "/changeStatus/{customizedPolicyId}")
    public ResponseEntity<String> changePolicyStatus(@PathVariable Long customizedPolicyId){
        String message = service.changePolicyStatus(customizedPolicyId);
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }

    @GetMapping(value = "/date")
    public ResponseEntity<List<Premium>> getPremiumByDate(@RequestParam String date) {
        List<Premium> premiums = service.getPremiumByDate(date);
        return new ResponseEntity<List<Premium>>(premiums, HttpStatus.OK);
    }

}
