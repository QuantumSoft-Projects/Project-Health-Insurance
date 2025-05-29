package com.quantumsoft.insurance.servicei;

import com.quantumsoft.insurance.dto.ResetPasswordRequest;
import com.quantumsoft.insurance.entity.Users;
import org.springframework.http.ResponseEntity;

public interface UserServiceI {

   //public String registerUser(Users user);

   String registerdUser(Users users);
   ResponseEntity<?> verifyEmail(String token);
   String loginUserWithEmailAndPassword(Users users);
   ResponseEntity<?> forgotPassword(String email);
   ResponseEntity<?> resetPassword(ResetPasswordRequest request);
}
