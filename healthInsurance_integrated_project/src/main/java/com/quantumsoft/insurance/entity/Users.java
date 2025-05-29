package com.quantumsoft.insurance.entity;

import com.quantumsoft.insurance.enums.AccountType;
import com.quantumsoft.insurance.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="user")
public class Users {

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> review;

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long userId;

    @NotBlank

    private String fullName;

    @NotBlank

    @Column(unique = true)

    private String email;

    @NotBlank

    private String phoneNumber;

    @NotBlank

    private String password;

    @NotBlank

    private String confirmPassword;

    @Enumerated(EnumType.STRING)

    private AccountType accountType;

    private boolean isEmailVerified;

    private String verificationToken;
    private String resetToken;

    private LocalDateTime tokenExpiry;
    @Enumerated(EnumType.STRING)
    private Role role;

    public Users(){}

    public Users(List<Review> review, Long userId, String fullName, String email, String phoneNumber, String password, String confirmPassword, AccountType accountType, boolean isEmailVerified, String verificationToken, String resetToken, LocalDateTime tokenExpiry, Role role) {
        this.review = review;
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.accountType = accountType;
        this.isEmailVerified = isEmailVerified;
        this.verificationToken = verificationToken;
        this.resetToken = resetToken;
        this.tokenExpiry = tokenExpiry;
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Review> getReview() {
        return review;
    }

    public void setReview(List<Review> review) {
        this.review = review;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public boolean isEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        isEmailVerified = emailVerified;
    }

    public String getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(String verificationToken) {
        this.verificationToken = verificationToken;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public LocalDateTime getTokenExpiry() {
        return tokenExpiry;
    }

    public void setTokenExpiry(LocalDateTime tokenExpiry) {
        this.tokenExpiry = tokenExpiry;
    }
}
