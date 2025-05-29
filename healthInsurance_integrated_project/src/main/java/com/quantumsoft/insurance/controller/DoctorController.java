package com.quantumsoft.insurance.controller;

import com.quantumsoft.insurance.entity.Doctor;
import com.quantumsoft.insurance.exception.UnAuthorizedAccessException;
import com.quantumsoft.insurance.servicei.DoctorService;
import com.quantumsoft.insurance.serviceimpl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/hospitals/{hospitalId}/doctors")
    public Doctor createDoctor(@PathVariable Long hospitalId, @RequestBody Doctor doctor, @RequestHeader("Authorization")String bearerToken) {
        String token = bearerToken.substring(7);
        String role = jwtService.extractRole(token);
        if(role.equals("SUPERADMIN"))
            return doctorService.createDoctor(hospitalId, doctor);
        else
            throw new UnAuthorizedAccessException("Unauthorized Access");
    }

    @PutMapping("/doctors/{doctorId}")
    public Doctor updateDoctor(@PathVariable Long doctorId, @RequestBody Doctor doctor, @RequestHeader("Authorization")String bearerToken) {
        String token = bearerToken.substring(7);
        String role = jwtService.extractRole(token);
        if(role.equals("SUPERADMIN"))
            return doctorService.updateDoctor(doctorId, doctor);
        else
            throw new UnAuthorizedAccessException("Unauthorized Access");
    }

    @DeleteMapping("/doctors/{doctorId}")
    public void deleteDoctor(@PathVariable Long doctorId, @RequestHeader("Authorization")String bearerToken) {
        String token = bearerToken.substring(7);
        String role = jwtService.extractRole(token);
        if(role.equals("SUPERADMIN"))
            doctorService.deleteDoctor(doctorId);
        else
            throw new UnAuthorizedAccessException("Unauthorized Access");
    }

    @GetMapping("/doctors/{doctorId}")
    public Doctor getDoctor(@PathVariable Long doctorId) {
        return doctorService.getDoctorById(doctorId);
    }

    @GetMapping("/doctors")
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/hospitals/{hospitalId}/doctors")
    public List<Doctor> getDoctorsByHospital(@PathVariable Long hospitalId) {
        return doctorService.getDoctorsByHospital(hospitalId);
    }

    @GetMapping("/doctors/search")
    public List<Doctor> searchDoctors(@RequestParam String specialization) {
        return doctorService.searchBySpecialization(specialization);
    }

    @GetMapping("/doctors/available")
    public List<Doctor> getAvailableDoctors() {
        return doctorService.getAvailableDoctors();
    }
}

