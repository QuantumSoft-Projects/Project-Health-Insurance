package com.quantumsoft.insurance.serviceimpl;

import com.quantumsoft.insurance.dto.ResetPasswordRequest;
import com.quantumsoft.insurance.entity.Users;
import com.quantumsoft.insurance.repository.UserRepository;
import com.quantumsoft.insurance.servicei.EmailService;
import com.quantumsoft.insurance.servicei.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserServiceI {

    @Autowired
    private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private EmailService emailService;

    @Autowired
    private JwtService jwtService;

//    @Override
//    public String registerUser(Users user) {
//        userRepository.save(user);
//        return "User registered successfully...!";
//    }

    @Override
    public String registerdUser(Users users) {
        if (userRepository.findByEmail(users.getEmail()).isPresent()) {
            return "Email is already registered.";
        }

        String encodedPassword = passwordEncoder.encode(users.getPassword());
        users.setPassword(encodedPassword);

        String token = UUID.randomUUID().toString();
        users.setVerificationToken(token);
        users.setTokenExpiry(LocalDateTime.now().plusHours(24));
        users.setEmailVerified(false);

        userRepository.save(users);

        String link = "http://localhost:8080/api/users/verify?token=" + token;
        emailService.sendSimpleMessage(users.getEmail(), "Email Verification", "Click the link to verify your email: " + link);

        return "User registered. Please check your email to verify your account.";
    }

    @Override
    public ResponseEntity<?> verifyEmail(String token) {
        Optional<Users> userOpt = userRepository.findByVerificationToken(token);
        if (userOpt.isPresent()) {
            Users user = userOpt.get();
            if (user.getTokenExpiry().isBefore(LocalDateTime.now())) {
                return ResponseEntity.badRequest().body("Token has expired.");
            }
            user.setEmailVerified(true);
            user.setVerificationToken(null);
            user.setTokenExpiry(null);
            userRepository.save(user);
            return ResponseEntity.ok("Email verified successfully.");
        }
        return ResponseEntity.badRequest().body("Invalid token.");
    }

    @Override
    public String loginUserWithEmailAndPassword(Users users) {
        Users user = userRepository.findByEmail(users.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found with provided email."));

        if (!user.isEmailVerified()) {
            throw new RuntimeException("Email not verified.");
        }

        if (!passwordEncoder.matches(users.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password.");
        }
        return jwtService.generateToken(user);
    }

    @Override
    public ResponseEntity<?> forgotPassword(String email) {
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email not found."));

        String token = UUID.randomUUID().toString();
        user.setResetToken(token);
        user.setTokenExpiry(LocalDateTime.now().plusHours(1));
        userRepository.save(user);

       // String link = "http://localhost:8080/api/users/reset-password?token=" + token;
        emailService.sendSimpleMessage(email, "Reset Your Password", "Token: " + token);

        return ResponseEntity.ok("Reset link sent to email.");
    }

    @Override
    public ResponseEntity<?> resetPassword(ResetPasswordRequest request) {
        Users users = userRepository.findByResetToken(request.getToken())
                .orElseThrow(() -> new RuntimeException("Invalid token."));

        if (users.getTokenExpiry().isBefore(LocalDateTime.now())) {
            return ResponseEntity.badRequest().body("Token expired.");
        }
        users.setPassword(passwordEncoder.encode(request.getNewPassword()));
        users.setConfirmPassword(request.getConfirmNewPassword());
        users.setResetToken(null);
        users.setTokenExpiry(null);
        userRepository.save(users);

        return ResponseEntity.ok("Password reset successfully.");
    }
}
