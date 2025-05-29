package com.quantumsoft.insurance.entity;

import com.quantumsoft.insurance.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long adminId;
    private String name;
    @Email(message = "Email should be in abc@example.com")
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Role role;
    private String otp;

    public Admin(){}

    public Admin(Long adminId, String name, String email, String password, Role role, String otp) {
        this.adminId = adminId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.otp = otp;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}