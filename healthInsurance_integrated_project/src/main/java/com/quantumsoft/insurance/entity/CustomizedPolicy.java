package com.quantumsoft.insurance.entity;

import com.quantumsoft.insurance.enums.PolicyStatus;
import com.quantumsoft.insurance.enums.PremiumPlan;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class CustomizedPolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customizedPolicyId;
    @OneToOne
    private Policy policy;
    @ManyToOne
    private Users user;
    @Enumerated(EnumType.STRING)
    private PremiumPlan premiumPlan;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Premium> premiums;
    private Double totalPremium;
    private LocalDate policyStartDate;
    private LocalDate policyEndDate;
    @OneToMany
    private List<AddOn> addOns;
    @OneToMany
    private List<Rider> riders;
    @Enumerated(EnumType.STRING)
    private PolicyStatus policyStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    public CustomizedPolicy(){}

    public CustomizedPolicy(Long customizedPolicyId, Policy policy, Users user, PremiumPlan premiumPlan, List<Premium> premiums, Double totalPremium, LocalDate policyStartDate, LocalDate policyEndDate, List<AddOn> addOns, List<Rider> riders, PolicyStatus policyStatus, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.customizedPolicyId = customizedPolicyId;
        this.policy = policy;
        this.user = user;
        this.premiumPlan = premiumPlan;
        this.premiums = premiums;
        this.totalPremium = totalPremium;
        this.policyStartDate = policyStartDate;
        this.policyEndDate = policyEndDate;
        this.addOns = addOns;
        this.riders = riders;
        this.policyStatus = policyStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getCustomizedPolicyId() {
        return customizedPolicyId;
    }

    public void setCustomizedPolicyId(Long customizedPolicyId) {
        this.customizedPolicyId = customizedPolicyId;
    }

    public Policy getPolicy() {
        return policy;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public PremiumPlan getPremiumPlan() {
        return premiumPlan;
    }

    public void setPremiumPlan(PremiumPlan premiumPlan) {
        this.premiumPlan = premiumPlan;
    }

    public List<Premium> getPremiums() {
        return premiums;
    }

    public void setPremiums(List<Premium> premiums) {
        this.premiums = premiums;
    }

    public Double getTotalPremium() {
        return totalPremium;
    }

    public void setTotalPremium(Double totalPremium) {
        this.totalPremium = totalPremium;
    }

    public LocalDate getPolicyStartDate() {
        return policyStartDate;
    }

    public void setPolicyStartDate(LocalDate policyStartDate) {
        this.policyStartDate = policyStartDate;
    }

    public LocalDate getPolicyEndDate() {
        return policyEndDate;
    }

    public void setPolicyEndDate(LocalDate policyEndDate) {
        this.policyEndDate = policyEndDate;
    }

    public List<AddOn> getAddOns() {
        return addOns;
    }

    public void setAddOns(List<AddOn> addOns) {
        this.addOns = addOns;
    }

    public List<Rider> getRiders() {
        return riders;
    }

    public void setRiders(List<Rider> riders) {
        this.riders = riders;
    }

    public PolicyStatus getPolicyStatus() {
        return policyStatus;
    }

    public void setPolicyStatus(PolicyStatus policyStatus) {
        this.policyStatus = policyStatus;
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
