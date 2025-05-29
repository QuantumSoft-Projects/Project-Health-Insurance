package com.quantumsoft.insurance.serviceimpl;

import com.quantumsoft.insurance.entity.Admin;
import com.quantumsoft.insurance.exception.AdminRecordNotFoundException;
import com.quantumsoft.insurance.repository.AdminRepository;
import com.quantumsoft.insurance.servicei.AdminServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class AdminServiceImpl implements AdminServiceI {

    @Autowired
    private AdminRepository repository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value(value = "${spring.mail.username}")
    private String fromEmail;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String registerAdmin(Admin admin) {
        repository.save(admin);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom(fromEmail);
        simpleMailMessage.setTo(admin.getEmail());
        simpleMailMessage.setSubject("Health Insurance App: Credentials");
        simpleMailMessage.setText("Your credentials are: \n\n" + "username: " + admin.getEmail() + "\n\n" + "password: " + admin.getPassword() + "\n\n" + "Before login you must need to reset the password");

        javaMailSender.send(simpleMailMessage);
        return "Admin has registered successfully...!";
    }

    @Override
    public String sendOtp(String email) {
        String otp = String.valueOf(100000 + new Random().nextInt(90000));
        Optional<Admin> optional = repository.findByEmail(email);
        if(optional.isPresent()){
            Admin admin = optional.get();
            admin.setOtp(otp);
            repository.save(admin);
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            simpleMailMessage.setFrom(fromEmail);
            simpleMailMessage.setTo(admin.getEmail());
            simpleMailMessage.setSubject("Health Insurance App: Reset Password OTP");
            simpleMailMessage.setText("Your OTP to reset password is " + otp);

            javaMailSender.send(simpleMailMessage);

            return "OTP is send to the admin email";
        }
        throw new AdminRecordNotFoundException("Admin record with given email is not found in database");
    }

    @Override
    public String resetPassword(String otp, String password, String confirmPassword) {
       Optional<Admin> optional = repository.findByOtp(otp);
       if(optional.isPresent()) {
           if(password.equals(confirmPassword)) {
               Admin admin = optional.get();
               admin.setOtp(null);
               String encodedPassword = passwordEncoder.encode(password);
               admin.setPassword(encodedPassword);
               repository.save(admin);
               //String token = jwtService.generateToken(admin.getEmail());
               return "Password has reset successfully...!";
           }else
               return "Password and ConfirmPassword not matching";
       }else
           return "Invalid OTP";
    }

    /*@Override
    public String resetPassword(String email, String password) {
        Optional<Admin> optional = repository.findByEmail(email);
        if(optional.isPresent()){
            Admin admin = optional.get();
            String encodedPassword = passwordEncoder.encode(password);
            admin.setPassword(encodedPassword);
            repository.save(admin);
            return "Password has reset successfully";
        }
        return "Invalid token";
    }*/

    @Override
    public String adminLogin(String email, String password) {
        Optional<Admin> optional = repository.findByEmail(email);
        if(optional.isPresent()){
            Admin admin = optional.get();
            System.out.println("password" + password);
            if(passwordEncoder.matches(password, admin.getPassword())){
               return jwtService.generateToken(admin);
            }else {
                return "Invalid Password";
            }
        }
        return "Invalid email";
    }
}
