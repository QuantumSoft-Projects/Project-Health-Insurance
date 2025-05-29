package com.quantumsoft.insurance.controller;

import com.quantumsoft.insurance.entity.Admin;
import com.quantumsoft.insurance.servicei.AdminServiceI;
import com.quantumsoft.insurance.serviceimpl.JwtService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private AdminServiceI service;

    @Autowired
    private JwtService jwtService;

    @PostMapping(value = "/register")
    public ResponseEntity<String> registerAdmin(@RequestHeader("Authorization")String bearerToken, @RequestBody Admin admin){
        String token = bearerToken.substring(7);
        String role = jwtService.extractRole(token);
        if(role.equals("SUPERADMIN")){
            String message = service.registerAdmin(admin);
            return new ResponseEntity<String>(message, HttpStatus.CREATED);
        }else
            return new ResponseEntity<String>("Unauthorized access", HttpStatus.UNAUTHORIZED);
    }

    @PatchMapping(value = "/sendOtp")
    public ResponseEntity<String> sendOtp(@RequestParam String email){
        String message = service.sendOtp(email);
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }

    @PatchMapping(value = "/resetPswd")
    public ResponseEntity<String> resetPassword(@RequestParam String otp, @RequestParam  String password, @RequestParam  String confirmPassword){
        String message = service.resetPassword(otp, password, confirmPassword);
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }

//    @PatchMapping(value = "/resetPswd")
//    public ResponseEntity<String> resetPassword(@RequestHeader("Authorization") String bearerToken, String password, String confirmPassword){
//        String token = bearerToken.substring(7);
//        Claims claims = jwtService.extractClaims(token);
//        String email = claims.get("email", String.class);
//        if(password.equals(confirmPassword)) {
//            String message = service.resetPassword(email, password);
//            return new ResponseEntity<String>(message, HttpStatus.OK);
//        }else
//            return new ResponseEntity<String>("Password and ConfirmPassword not matching", HttpStatus.NOT_ACCEPTABLE);
//    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> adminLogin(@RequestParam String email, @RequestParam String password){
        String message = service.adminLogin(email, password);
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }

}
