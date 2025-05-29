package com.quantumsoft.insurance.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class SupportAgent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long supportAgentId;
    private String name;
    @Email(message = "Email should be in abc@example.com")
    private String email;
    @Pattern(regexp = "^(\\+91|91)?[6-9][0-9]{9}", message = "Invalid mobile number")
    private String phone;
    @OneToMany
    private List<SupportTicket> supportTickets;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    public SupportAgent(){}

    public SupportAgent(Long supportAgentId, String name, String email, String phone, List<SupportTicket> supportTickets, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.supportAgentId = supportAgentId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.supportTickets = supportTickets;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getSupportAgentId() {
        return supportAgentId;
    }

    public void setSupportAgentId(Long supportAgentId) {
        this.supportAgentId = supportAgentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public @Email(message = "Email should be in abc@example.com") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Email should be in abc@example.com") String email) {
        this.email = email;
    }

    public @Pattern(regexp = "^(\\+91|91)?[6-9][0-9]{9}", message = "Invalid mobile number") String getPhone() {
        return phone;
    }

    public void setPhone(@Pattern(regexp = "^(\\+91|91)?[6-9][0-9]{9}", message = "Invalid mobile number") String phone) {
        this.phone = phone;
    }

    public List<SupportTicket> getSupportTickets() {
        return supportTickets;
    }

    public void setSupportTickets(List<SupportTicket> supportTickets) {
        this.supportTickets = supportTickets;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
