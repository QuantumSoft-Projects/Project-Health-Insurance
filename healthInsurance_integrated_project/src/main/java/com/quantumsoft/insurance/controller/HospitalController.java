package com.quantumsoft.insurance.controller;

import com.quantumsoft.insurance.entity.Hospital;
import com.quantumsoft.insurance.exception.UnAuthorizedAccessException;
import com.quantumsoft.insurance.servicei.HospitalService;
import com.quantumsoft.insurance.serviceimpl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospitals")
@CrossOrigin("*")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private JwtService jwtService;

    @PostMapping
    public Hospital addHospital(@RequestBody Hospital hospital, @RequestHeader("Authorization")String bearerToken) {
        String token = bearerToken.substring(7);
        String role = jwtService.extractRole(token);
        if(role.equals("SUPERADMIN"))
            return hospitalService.addHospital(hospital);
        else
            throw new UnAuthorizedAccessException("Unauthorized Access");
    }

    @PutMapping("/{id}")
    public Hospital updateHospital(@PathVariable Long id, @RequestBody Hospital hospital, @RequestHeader("Authorization")String bearerToken) {
        String token = bearerToken.substring(7);
        String role = jwtService.extractRole(token);
        if(role.equals("SUPERADMIN"))
            return hospitalService.updateHospital(id, hospital);
        else
            throw new UnAuthorizedAccessException("Unauthorized Access");
    }

    @GetMapping("/{id}")
    public Hospital getHospitalById(@PathVariable Long id) {
        return hospitalService.getHospitalById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteHospital(@PathVariable Long id, @RequestHeader("Authorization")String bearerToken) {
        String token = bearerToken.substring(7);
        String role = jwtService.extractRole(token);
        if(role.equals("SUPERADMIN"))
            hospitalService.deleteHospital(id);
        else
            throw new UnAuthorizedAccessException("Unauthorized Access");
    }

    @GetMapping
    public List<Hospital> getAllHospitals() {
        return hospitalService.getAllHospitals();
    }

    @GetMapping("/city/{city}")
    public List<Hospital> getHospitalsByCity(@PathVariable String city) {
        return hospitalService.getHospitalsByCity(city);
    }

    @GetMapping("/network")
    public List<Hospital> getNetworkHospitals() {
        return hospitalService.getNetworkHospitals();
    }

    @GetMapping("/icu")
    public List<Hospital> getHospitalsWithICU() {
        return hospitalService.getHospitalsWithICU();
    }
}
