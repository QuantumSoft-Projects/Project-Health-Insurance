package com.quantumsoft.insurance.controller;

import com.quantumsoft.insurance.entity.AddOn;
import com.quantumsoft.insurance.servicei.AddOnServiceI;
import com.quantumsoft.insurance.serviceimpl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/addOn")
@CrossOrigin("*")
public class AddOnnController {

    @Autowired
    private AddOnServiceI service;

    @Autowired
    private JwtService jwtService;

    @PostMapping(value = "/add")
    public ResponseEntity<String> addAddOn(@RequestBody AddOn addOn, @RequestHeader("Authorization")String bearerToken){
        String token = bearerToken.substring(7);
        String role = jwtService.extractRole(token);
        if(role.equals("SUPERADMIN")) {
            String message = service.addAddOn(addOn);
            return new ResponseEntity<String>(message, HttpStatus.CREATED);
        }else
            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping(value = "/fetchAll")
    public ResponseEntity<List<AddOn>> fetchAllAddOns(){
        List<AddOn> addOns = service.fetchAllAddOns();
        return new ResponseEntity<List<AddOn>>(addOns, HttpStatus.OK);
    }
}
