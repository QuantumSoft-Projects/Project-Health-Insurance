package com.quantumsoft.insurance.controller;

import com.quantumsoft.insurance.entity.Rider;
import com.quantumsoft.insurance.servicei.RiderServiceI;
import com.quantumsoft.insurance.serviceimpl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/rider")
@CrossOrigin("*")
public class RiderController {

    @Autowired
    private RiderServiceI service;

    @Autowired
    private JwtService jwtService;

    @PostMapping(value = "/add")
    public ResponseEntity<String> addRider(@RequestBody Rider rider, @RequestHeader("Authorization")String bearerToken){
        String token = bearerToken.substring(7);
        String role = jwtService.extractRole(token);
        if(role.equals("SUPERADMIN")) {
            String message = service.addRider(rider);
            return new ResponseEntity<String>(message, HttpStatus.CREATED);
        }else
            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping(value = "/fetchAll")
    public ResponseEntity<List<Rider>> fetchAllRiders(){
        List<Rider> riders = service.fetchAllRiders();
        return new ResponseEntity<List<Rider>>(riders, HttpStatus.OK);
    }
}
