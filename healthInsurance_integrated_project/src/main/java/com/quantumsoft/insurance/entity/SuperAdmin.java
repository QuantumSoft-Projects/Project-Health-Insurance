package com.quantumsoft.insurance.entity;

import com.quantumsoft.insurance.enums.Role;
import jakarta.persistence.*;

@Entity
public class SuperAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long superAdminId;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    public SuperAdmin(){}

    public SuperAdmin(Long superAdminId, String username, String password, Role role) {
        this.superAdminId = superAdminId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Long getSuperAdminId() {
        return superAdminId;
    }

    public void setSuperAdminId(Long superAdminId) {
        this.superAdminId = superAdminId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
