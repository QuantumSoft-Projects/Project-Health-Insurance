package com.quantumsoft.insurance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Rider {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long riderId;
    private String name;
    private String description;
    private Double premiumPerYear;

    public Rider(){}

    public Rider(Long riderId, String name, String description, Double premiumPerYear) {
        this.riderId = riderId;
        this.name = name;
        this.description = description;
        this.premiumPerYear = premiumPerYear;
    }

    public Long getRiderId() {
        return riderId;
    }

    public void setRiderId(Long riderId) {
        this.riderId = riderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPremiumPerYear() {
        return premiumPerYear;
    }

    public void setPremiumPerYear(Double premiumPerYear) {
        this.premiumPerYear = premiumPerYear;
    }
}
