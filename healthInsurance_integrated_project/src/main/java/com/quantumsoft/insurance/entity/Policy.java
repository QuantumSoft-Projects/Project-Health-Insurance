package com.quantumsoft.insurance.entity;

import com.quantumsoft.insurance.enums.PolicyType;
import com.quantumsoft.insurance.enums.PremiumPlan;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long policyId;
    @NotNull(message = "Policy name cannot be null")
    private String policyName;
    @Enumerated(EnumType.STRING)
    private PolicyType policyType;
    private String description;
    private double coverageAmount;
    private double premium;
    @Enumerated(EnumType.STRING)
    private PremiumPlan premiumPlan; // added by Akshay
    private int termDuration;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    public Policy(){}

    public Policy(Long policyId, String policyName, PolicyType policyType, String description, double coverageAmount, double premium, PremiumPlan premiumPlan, int termDuration, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.policyId = policyId;
        this.policyName = policyName;
        this.policyType = policyType;
        this.description = description;
        this.coverageAmount = coverageAmount;
        this.premium = premium;
        this.premiumPlan = premiumPlan;
        this.termDuration = termDuration;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Long policyId) {
        this.policyId = policyId;
    }

    public @NotNull(message = "Policy name cannot be null") String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(@NotNull(message = "Policy name cannot be null") String policyName) {
        this.policyName = policyName;
    }

    public PolicyType getPolicyType() {
        return policyType;
    }

    public void setPolicyType(PolicyType policyType) {
        this.policyType = policyType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCoverageAmount() {
        return coverageAmount;
    }

    public void setCoverageAmount(double coverageAmount) {
        this.coverageAmount = coverageAmount;
    }

    public double getPremium() {
        return premium;
    }

    public void setPremium(double premium) {
        this.premium = premium;
    }

    public PremiumPlan getPremiumPlan() {
        return premiumPlan;
    }

    public void setPremiumPlan(PremiumPlan premiumPlan) {
        this.premiumPlan = premiumPlan;
    }

    public int getTermDuration() {
        return termDuration;
    }

    public void setTermDuration(int termDuration) {
        this.termDuration = termDuration;
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
