package com.quantumsoft.insurance.controller;

import com.quantumsoft.insurance.dto.ResetPasswordRequest;
import com.quantumsoft.insurance.entity.Users;
import com.quantumsoft.insurance.servicei.UserServiceI;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserServiceI userService;

//    @PostMapping(value = "/register")
//    public ResponseEntity<String> registerUser(@Valid @RequestBody Users user){
//       String message = userService.registerUser(user);
//       return new ResponseEntity<String>(message, HttpStatus.CREATED);
//    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Users users) {
        return new ResponseEntity<>(userService.registerdUser(users), HttpStatus.CREATED);
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestParam("token") String token) {
        return userService.verifyEmail(token);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody Users users) {
        return new ResponseEntity<String>(userService.loginUserWithEmailAndPassword(users), HttpStatus.OK);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam("email") String email) {
        return userService.forgotPassword(email);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest request) {
        return userService.resetPassword(request);
    }
}
