package com.quantumsoft.insurance.controller;

import com.quantumsoft.insurance.entity.SuperAdmin;

import com.quantumsoft.insurance.serviceimpl.JwtService;

import com.quantumsoft.insurance.serviceimpl.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping(value = "/api/superadmin")

@CrossOrigin("*")

public class SuperAdminController {

    @Autowired

    private UserDetailsServiceImpl userDetailsService;

    @Autowired

    private AuthenticationManager authenticationManager;

    @Autowired

    private JwtService jwtService;

    @PostMapping(value = "/login")

    public ResponseEntity<String> superadminLogin(@RequestBody SuperAdmin superAdmin) {

        String username = superAdmin.getUsername();

        String password = superAdmin.getPassword();

        UsernamePasswordAuthenticationToken token =

                new UsernamePasswordAuthenticationToken(username, password);

        try {

            Authentication authenticate = authenticationManager.authenticate(token);

            if (authenticate.isAuthenticated()) {

                if (username.equals("superadmin")) {

                    SuperAdmin sa = userDetailsService.getSuperAdmin(username);

                    String jwtToken = jwtService.generateToken(username, sa);

                    return new ResponseEntity<>(jwtToken, HttpStatus.OK);

                } else {

                    return new ResponseEntity<>("Admin logged in successfully..!", HttpStatus.OK);

                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return new ResponseEntity<>("Invalid Credentials", HttpStatus.NOT_ACCEPTABLE);

    }

}
